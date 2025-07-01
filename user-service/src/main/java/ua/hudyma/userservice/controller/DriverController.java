package ua.hudyma.userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/profile/{userId}")
    public Profile getProfile (@PathVariable String userId){
        return driverService.getProfileByDriverId(userId);
    }

    @GetMapping("/exists/{userId}")
    public boolean existsById (@PathVariable String userId){
        return driverService.existsById (userId);
    }

    @GetMapping("/getAllTrips/{userId}")
    public List<Trip> getTripByDriverId (@PathVariable String userId){
        return driverService.getAllTripsByDriverId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addDriver (@RequestBody Driver driver){
        driverService.persist (driver);
    }
}
