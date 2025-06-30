package ua.hudyma.tripservice.service;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.hudyma.tripservice.client.UserClient;
import ua.hudyma.tripservice.domain.TripStatus;
import ua.hudyma.tripservice.repository.TripRepository;
import ua.hudyma.tripservice.domain.Trip;
import ua.hudyma.tripservice.util.DistanceCalculator;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Log4j2
public class TripService {

    private final TripRepository tripRepository;
    private final CityService cityService;
    private final UserClient userClient;

    @Value("${trip.base-price}")
    private Double price;

    public Optional<Trip> getTripById(String id) {
        return tripRepository.findById(id);
    }

    public boolean existsByDriverId(Long driverId) {
        return userClient.existsById(driverId);
    }

    public boolean existsById(String tripId) {
        return tripRepository.existsById(tripId);
    }

    public List<Trip> getAllTripsByDriverId(Long driverId) {
        return tripRepository.findAllByDriverId(driverId);
    }

    public void persistTripForDriver(Trip trip, Long driverId, String depId, String destId) {
        var depCity = cityService.getCityById(depId);
        var destCity = cityService.getCityById(destId);
        if (userClient.existsById(driverId)){
            trip.setDriverId(driverId);
            depCity.ifPresent(trip::setDeparture);
            destCity.ifPresent(trip::setDestination);
            applyGeneratedData(trip);
            trip.setDriverId(driverId);
            trip.setTripCreated(LocalDateTime.now());
            trip.setStatus(TripStatus.WAITING_CONFIRMATION);
            tripRepository.save(trip);
        }
        else {
            log.error("driver {} not found", driverId);
        }

    }

    private void applyGeneratedData(Trip trip) {
        if (trip.getOptimalDistance() == null || trip.getOptimalDistance() == 0d) {
            trip.setOptimalDistance(
                    DistanceService.getDistance(
                            trip.getDeparture(), trip.getDestination()));
        }
        if (trip.getPrice() == null || trip.getPrice() == 0) {
            trip.setPrice((int) Math.floor(trip.getOptimalDistance() * price));
        }
        if (trip.getId() == null || trip.getId().isEmpty()) {
            Random random = new SecureRandom();
            trip.setId(NanoIdUtils.randomNanoId(
                    random, NanoIdUtils.DEFAULT_ALPHABET, 16));
        }
        if (trip.getDirectDistance() == null || trip.getDirectDistance() == 0d) {
            trip.setDirectDistance(
                    DistanceCalculator
                            .haversine(trip.getDestination(),
                                    trip.getDeparture()));
        }
    }

    public void setStatus(Trip trip, TripStatus status) {
        trip.setStatus(status);
        //when obj received from repo within TX IN THIS METHOD!!, no saving is necessary
        //in this case it's received as argument beyond manageble tx, therefore it needs to be saved explicitly
        //make sure to annotate it with @Transactional
        tripRepository.save(trip);
    }
}
