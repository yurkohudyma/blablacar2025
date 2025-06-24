package ua.hudyma.tripservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.tripservice.domain.Trip;
import ua.hudyma.tripservice.service.CityService;
import ua.hudyma.tripservice.service.TripService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/trips")
public class TripController {

    private final TripService tripService;
    private final CityService cityService;

    @GetMapping("/{id}")
    public Trip getTripById (@PathVariable String id){
        return tripService.getTripById(id);
    }

    @GetMapping("/getAllTrips/{driverId}")
    public List<Trip> getTripByDriverId(@PathVariable Long driverId) {
        return tripService.getAllTripsByDriverId(driverId);
    }

    @PostMapping("/add/{driverId}/{depId}/{destId}")
    public void addTripForDriverId (@RequestBody Trip trip,
                                    @PathVariable Long driverId,
                                    @PathVariable String depId,
                                    @PathVariable String destId){
        var depCity = cityService.getCityById (depId);
        var destCity = cityService.getCityById(destId);
        trip.setDriverId(driverId);
        trip.setDeparture(depCity);
        trip.setDestination(destCity);
        tripService.persistTripForDriver(trip, driverId);
    }
}
