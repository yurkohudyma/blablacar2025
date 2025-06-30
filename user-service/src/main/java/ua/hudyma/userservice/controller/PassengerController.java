package ua.hudyma.userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Passenger> getPassengersListByTripId (
            @PathVariable String tripId){
        return passengerService.getPassengerListByTripId (tripId);
    }

    @PostMapping("/{tripId}")
    public void addPassenger (@RequestBody Passenger passenger,
                              @PathVariable String tripId){
        passengerService.persist(passenger, tripId);
    }

    @PatchMapping("/{passengerId}/{tripId}")
    public ResponseEntity<?> setPassengerForTrip (@PathVariable Long passengerId,
                                                        @PathVariable String tripId){
        var passed = passengerService.assignPassengerToTrip(passengerId, tripId);
        return passed
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("Passenger %d not found", passengerId));

    }
}
