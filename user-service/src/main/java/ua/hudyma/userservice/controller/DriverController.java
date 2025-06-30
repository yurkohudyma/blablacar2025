package ua.hudyma.userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.tripservice.domain.Trip;
import ua.hudyma.userservice.domain.Driver;
import ua.hudyma.userservice.domain.Profile;
import ua.hudyma.userservice.service.DriverService;

import java.util.List;

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

    @GetMapping("/exists/{driverId}")
    public boolean existsById (@PathVariable Long driverId){
        return driverService.existsById (driverId);
    }

    @GetMapping("/getAllTrips/{driverId}")
    public List<Trip> getTripByDriverId (@PathVariable Long driverId){
        return driverService.getAllTripsByDriverId(driverId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addDriver (@RequestBody Driver driver){
        driverService.persist (driver);
    }
}
