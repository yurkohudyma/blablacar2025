package ua.hudyma.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import ua.hudyma.tripservice.domain.Trip;
import ua.hudyma.userservice.client.TripClient;
import ua.hudyma.userservice.domain.Driver;
import ua.hudyma.userservice.domain.Profile;
import ua.hudyma.userservice.repository.DriverRepository;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class DriverService {

    private final TripClient tripClient;
    private final DriverRepository driverRepository;

    @GetMapping
    public List<Trip> getAllTripsByDriverId(Long driverId){
        return tripClient.findAllByDriverId(driverId);
    }

    public Profile getProfileByDriverId (Long driverId){
        var driver = driverRepository.findById(driverId).orElseThrow();
        return driver.getProfile();
    }

    public void persist(Driver driver, String tripId) {
        driver.setTripId(tripId);
        driverRepository.save(driver);
    }

    public Optional<Driver> getDriverById(Long driverId) {
        return driverRepository.findById(driverId);
    }

    public Optional<Driver> getDriverByTripId(String tripId) {
        return driverRepository.findByTripId(tripId);
    }
}
