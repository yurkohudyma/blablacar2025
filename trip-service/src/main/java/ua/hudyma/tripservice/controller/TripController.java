package ua.hudyma.tripservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.tripservice.domain.Trip;
import ua.hudyma.tripservice.domain.TripStatus;
import ua.hudyma.tripservice.service.CityService;
import ua.hudyma.tripservice.service.TripService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/trips")
public class TripController {

    private final TripService tripService;


    @GetMapping("/{id}")
    public Optional<Trip> getTripById(@PathVariable String id) {
        return tripService.getTripById(id);
    }

    @GetMapping("/getAllTrips/{driverId}")
    public List<Trip> getTripByDriverId(@PathVariable Long driverId) {
        return tripService.getAllTripsByDriverId(driverId);
    }

    @GetMapping("/exists/{tripId}")
    public boolean existsById(@PathVariable String tripId) {
        return tripService.existsById(tripId);
    }

    @GetMapping("/existsByDriverId/{driverId}")
    public boolean existsById(@PathVariable Long driverId) {
        return tripService.existsByDriverId(driverId);
    }

    @PostMapping("/add/{driverId}/{depId}/{destId}")
    public void addTrip (@RequestBody Trip trip,
                                   @PathVariable Long driverId,
                                   @PathVariable String depId,
                                   @PathVariable String destId) {

        tripService.persistTripForDriver(trip, driverId, depId, destId);
    }

    @PatchMapping("/setStatus/{tripId}/{status}")
    public void setTripStatus(@PathVariable String tripId,
                              @PathVariable TripStatus status) {
        tripService
                .getTripById(tripId)
                .ifPresentOrElse(trip ->
                                tripService.setStatus(trip, status),
                        () -> log.error("trip {} not found", tripId)
                );
    }
}
