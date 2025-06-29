package ua.hudyma.userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.userservice.domain.Passenger;
import ua.hudyma.userservice.service.PassengerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/passengers")
@Log4j2
public class PassengerController {
    private final PassengerService passengerService;

    @GetMapping("/{tripId}")
    public List<Passenger> getPassengersListByTripId (@PathVariable String tripId){
        return passengerService.getPassengerListByTripId (tripId);
    }

    @PostMapping("/{tripId}")
    public void addPassenger (@RequestBody Passenger passenger,
                              @PathVariable String tripId){
        passengerService.persist(passenger, tripId);
    }

    @PatchMapping("/{passengerId}/{tripId}")
    public void setPassengerForTrip (@PathVariable Long passengerId,
                                     @PathVariable String tripId){
        var passenger = passengerService.getById(passengerId);
        var tripExists = passengerService.checkIfExists(tripId);
        if (passenger.isEmpty()){
            log.error("Passenger {} not found", passengerId);
        } else if (!tripExists) {
            log.error("Trip {} not found", tripId);
        } else {
            passengerService.persist(passenger.get(), tripId);
        }
    }

}
