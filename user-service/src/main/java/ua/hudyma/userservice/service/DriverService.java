package ua.hudyma.userservice.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.hudyma.tripservice.domain.Trip;
import ua.hudyma.userservice.client.TripClient;
import ua.hudyma.userservice.domain.Driver;
import ua.hudyma.userservice.domain.ExperienceLevel;
import ua.hudyma.userservice.domain.Profile;
import ua.hudyma.userservice.dto.ReviewDto;
import ua.hudyma.userservice.repository.DriverRepository;

import java.net.URI;
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

    public List<ReviewDto> getAllReviewsForDriverIdAndTripId (String driverId, String tripId){
        var instances = discoveryClient.getInstances("rating-service");
        ServiceInstance serviceInstance = instances.get(0);
        var uri = serviceInstance.getUri() + "/reviews/{driverId}/{tripId}";
        return restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ReviewDto>>(){},
                driverId,
                tripId).getBody();
    }

    public List<Trip> getAllTripsByDriverId(String driverId){
        checkEureka();
        try {
            return tripClient.findAllByDriverId(driverId);
        } catch (Exception e) {
            log.error("Failed to fetch trips for driver {}: {}",
                    driverId, e.getMessage());
            return Collections.emptyList();
        }
    }

    public Profile getProfileByDriverId (String userId){
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
}
