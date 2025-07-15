package ua.hudyma.userservice.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.hudyma.tripservice.domain.Trip;
import ua.hudyma.userservice.client.TripClient;
import ua.hudyma.userservice.domain.Car;
import ua.hudyma.userservice.domain.Driver;
import ua.hudyma.userservice.domain.ExperienceLevel;
import ua.hudyma.userservice.domain.Profile;
import ua.hudyma.userservice.dto.DriverDto;
import ua.hudyma.userservice.dto.ReviewDto;
import ua.hudyma.userservice.dto.TripDto;
import ua.hudyma.userservice.mapper.DriverMapper;
import ua.hudyma.userservice.repository.DriverRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class DriverService {

    private final TripClient tripClient;
    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;
    private final CircuitBreakerFactory<?, ?> circuitBreakerFactory;
    private final DriverRepository driverRepository;

    @PostConstruct
    public void checkEureka() {
        List<ServiceInstance> instances = discoveryClient
                .getInstances("trip-service");
        if (instances.isEmpty()) {
            log.warn("No instances of trip-service found in Eureka!");
        } else {
            log.info("trip-service instances found: {}",
                    instances.size());
        }
    }

    /**
     * Discovery Client assisted call
     */
    public List<ReviewDto> getAllReviewsForDriverIdAndTripId(String driverId, String tripId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String token = null;
        if (auth instanceof JwtAuthenticationToken jwtAuth) {
            token = jwtAuth.getToken().getTokenValue();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        if (token != null) {
            headers.setBearerAuth(token);
        }
        HttpEntity<?> entity = new HttpEntity<>(headers);
        var instances = discoveryClient.getInstances("rating-service");
        if (instances == null || instances.isEmpty()) {
            throw new IllegalStateException("rating-service not found");
        }
        var serviceInstance = instances.get(0);
        var uri = serviceInstance.getUri() + "/reviews/{driverId}/{tripId}";

        return circuitBreakerFactory
                .create("user-service")
                .run(() -> restTemplate.exchange(
                                uri,
                                HttpMethod.GET,
                                entity,
                                new ParameterizedTypeReference<List<ReviewDto>>() {
                                },
                                driverId, tripId),
                        throwable -> new ResponseEntity<>(
                                List.of(new ReviewDto(null, null, null, "CIRCUIT-BREAKER")),
                                HttpStatus.OK))
                .getBody();
    }

    /**
     * Feign Client assisted call
     */
    public List<Trip> getAllTripsByDriverId(String driverId) {
        checkEureka();
        try {
            return tripClient.findAllByDriverId(driverId);
        } catch (Exception e) {
            log.error("Failed to fetch trips for driver {}: {}",
                    driverId, e.getMessage());
            return Collections.emptyList();
        }
    }

    public Profile getProfileByDriverId(String userId) {
        var driver = driverRepository
                .findById(userId).orElseThrow();
        return driver.getProfile();
    }

    public void persist(Driver driver) {
        driver.setExpLevel(ExperienceLevel.NEWCOMER);
        driverRepository.save(driver);
    }

    public Optional<Driver> getDriverById(String userId) {
        return driverRepository.findById(userId);
    }

    /**
     * Inbound Feign Client assisted call from tripservice
     */
    public DriverDto getDriverDtoById(String userId) {
        return driverRepository
                .findById(userId)
                .map(DriverMapper.INSTANCE::toDto)
                .orElseGet(DriverDto::new);
    }


    public boolean checkIfExists(String tripId) {
        checkEureka();
        try {
            return tripClient.existsById(tripId);
        } catch (Exception e) {
            log.error("Failed to fetch trip {}: {}",
                    tripId, e.getMessage());
        }
        log.error("error finding trip {}", tripId);
        return false;
    }

    public boolean existsById(String userId) {
        return driverRepository.existsById(userId);
    }

    public Driver getDriverByTripId(String tripId) {
        var instances = discoveryClient.getInstances("trip-service");
        if (instances == null || instances.isEmpty()) {
            return new Driver();
        }
        var serviceInstance = instances.get(0);
        var uri = serviceInstance.getUri() + "/trips/{id}";
        var trip = Optional.of(
                restTemplate
                        .getForObject(uri, TripDto.class, tripId));
        return trip.map(tripDto ->
                        driverRepository
                                .findByUserId(tripDto.driverId())
                                .orElse(new Driver()))
                .orElseGet(Driver::new);
    }

    public List<Car> getAllDriversCarsByDriverId(String driverId) {
        var driver = driverRepository.findById(driverId);
        if (driver.isPresent()) {
            return driver.get().getCarList();
        } else {
            log.error("driver {} not found", driverId);
            return Collections.emptyList();
        }
    }

    /**
     * Inbound Feign Client assisted call from tripservice
     */
    public void updateUserTripQty(String userId) {
        //todo distinguish driver or passenger
        //todo a user with given userId could be both Driver and Passenger

        var user = driverRepository.findById(userId);
        if (user.isEmpty()) {
            //passengerService.findPassengerAndUpdateTripQty(userId);
            //todo inject passenger service, as it bring circular dependency
            log.error("driver with {} do not EXIST, proceed with Passenger", userId);
        }
        else {
            var usr = user.get();
            if (usr.getTripQuantity() == null) {
                usr.setTripQuantity(0L);
                log.info("driver's trip qty set to ZERO");
            } else {
                usr.setTripQuantity(usr.getTripQuantity() + 1L);
                log.info("driver's trip qty INCREMENTED");
            }
            driverRepository.save(usr);
        }
    }
}
