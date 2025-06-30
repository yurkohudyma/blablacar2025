package ua.hudyma.tripservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.tripservice.domain.TripStatus;
import ua.hudyma.tripservice.repository.TripRepository;
import ua.hudyma.tripservice.domain.Trip;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class TripService {

    private final TripRepository tripRepository;

    public Optional<Trip> getTripById(String id) {
        return tripRepository.findById(id);
    }

    public boolean existsById(String tripId) {
        return tripRepository.existsById(tripId);
    }

    public List<Trip> getAllTripsByDriverId(Long driverId) {
        return tripRepository.findAllByDriverId(driverId);
    }

    public void persistTripForDriver(Trip trip, Long driverId) {
        trip.setDriverId(driverId);
        trip.setTripCreated(LocalDateTime.now());
        tripRepository.save(trip);
    }
    public void setStatus(Trip trip, TripStatus status) {
        trip.setStatus(status);
        //obj received from repo with TX (IN THIS METHOD!!), no saving is necessary
        tripRepository.save(trip);
    }
}
