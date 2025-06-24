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

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/drivers")
public class DriverController {
    private final DriverService driverService;
    private final DriverRepository driverRepository;
    @GetMapping("/profile/{driverId}")
    public Profile getProfile (@PathVariable Long driverId){
        return driverService.getProfileByDriverId(driverId);
    }

    @GetMapping("/getAllTrips/{driverId}")
    public List<Trip> getTripByDriverId (@PathVariable Long driverId){
        return driverService.getAllTripsByDriverId(driverId);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addDriver (@RequestBody Driver driver){
        driverRepository.save(driver);
    }
}
