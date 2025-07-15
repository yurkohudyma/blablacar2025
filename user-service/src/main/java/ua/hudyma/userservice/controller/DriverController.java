package ua.hudyma.userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.tripservice.domain.Trip;
import ua.hudyma.userservice.domain.Car;
import ua.hudyma.userservice.domain.Driver;
import ua.hudyma.userservice.domain.Profile;
import ua.hudyma.userservice.dto.DriverDto;
import ua.hudyma.userservice.dto.ReviewDto;
import ua.hudyma.userservice.service.DriverService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/drivers")
public class DriverController {
    private final DriverService driverService;

    /**
     * Inbound Feign Client assisted call from tripservice
     */
    /*@PostMapping("/save")
    public void persistDriverDto (@RequestBody DriverDto driverDto){
        driverService.persist(DriverMapper.INSTANCE.toEntity(driverDto));
    }*/

    /**
     * Inbound Feign Client assisted call from tripservice
     */
    @PostMapping("/updateTripQty/{userId}")
    public void updateUserTripQty (@PathVariable String userId){
        driverService.updateUserTripQty (userId);
    }

    /**
     * Inbound Feign Client assisted call from tripservice
     */
    @GetMapping("/getDriverDto/{userId}")
    public DriverDto findById (@PathVariable String userId){
        return driverService.getDriverDtoById(userId);
    }

    @GetMapping("/public/hello")
    public String publicHello() {
        return "Hello from public endpoint!";
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping("/role")
    public String roleProtectedEndpoint() {
        return "Hello from role protected endpoint!";
    }

    @GetMapping("/getAllCars/{driverId}")
    public List<Car> getAllDriversCars (@PathVariable String driverId){
        return driverService.getAllDriversCarsByDriverId(driverId);
    }

    @GetMapping("/profile/{userId}")
    public Profile getProfile (@PathVariable String userId){
        return driverService.getProfileByDriverId(userId);
    }

    @GetMapping("/exists/{userId}")
    public boolean existsById (@PathVariable String userId){
        return driverService.existsById (userId);
    }

    /**
     * Feign Client assisted call
     */
    @GetMapping("/getAllTrips/{userId}")
    public List<Trip> getTripByDriverId (@PathVariable String userId){
        return driverService.getAllTripsByDriverId(userId);
    }

    @GetMapping("/{tripId}")
    public Driver getDriverByTripId (@PathVariable String tripId){
        return driverService.getDriverByTripId(tripId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addDriver (@RequestBody Driver driver){
        driverService.persist (driver);
    }

    /**
     * Discovery Client assisted call
     */
    @GetMapping("/reviews/{driverId}/{tripId}")
    public List<ReviewDto> getAllReviewsForDriverAndTrip (@PathVariable String driverId,
                                                          @PathVariable String tripId){
        return driverService.getAllReviewsForDriverIdAndTripId(driverId, tripId);
    }

}
