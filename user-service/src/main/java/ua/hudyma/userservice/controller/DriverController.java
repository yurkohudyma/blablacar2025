package ua.hudyma.userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.tripservice.domain.Trip;
import ua.hudyma.userservice.domain.Driver;
import ua.hudyma.userservice.domain.Profile;
import ua.hudyma.userservice.repository.DriverRepository;
import ua.hudyma.userservice.service.DriverService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/drivers")
public class DriverController {
    private final DriverService driverService;

    @GetMapping("/profile/{driverId}")
    public Profile getProfile (@PathVariable Long driverId){
        return driverService.getProfileByDriverId(driverId);
    }

    @GetMapping("/{tripId}")
    public Optional<Driver> getDriverByTripId (@PathVariable String tripId){
        return driverService.getDriverByTripId(tripId);
    }

    @GetMapping("/getAllTrips/{driverId}")
    public List<Trip> getTripByDriverId (@PathVariable Long driverId){
        return driverService.getAllTripsByDriverId(driverId);
    }

    @PostMapping("/{tripId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addDriver (@RequestBody Driver driver, @PathVariable String tripId){
        driverService.persist (driver, tripId);
    }

    @PatchMapping("/{driverId}/{tripId}")
    public void setDriverForTrip (@PathVariable Long driverId, @PathVariable String tripId){
        var driver = driverService.getDriverById (driverId);
        var tripExists = driverService.checkIfExists (tripId);
        if (driver.isEmpty()){
            log.error("Driver {} not found", driverId);
        } else if (!tripExists) {
            log.error("Trip {} not found", tripId);
        } else {
            driverService.persist(driver.get(), tripId);

        }
    }
}
