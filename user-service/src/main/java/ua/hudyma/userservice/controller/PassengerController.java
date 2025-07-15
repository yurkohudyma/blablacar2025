package ua.hudyma.userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.userservice.domain.EventType;
import ua.hudyma.userservice.domain.Passenger;
import ua.hudyma.userservice.dto.EventDto;
import ua.hudyma.userservice.dto.UserSmallDto;
import ua.hudyma.userservice.service.NotificationService;
import ua.hudyma.userservice.service.PassengerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/passengers")
@Log4j2
public class PassengerController {
    private final PassengerService passengerService;
    private final NotificationService notificationService;

    @GetMapping("/{tripId}")
    public List<Passenger> getPassengersListByTripId(
            @PathVariable String tripId) {
        return passengerService.getPassengerListByTripId(tripId);
    }

    @PostMapping("/{tripId}")
    public void addPassenger(@RequestBody Passenger passenger,
                             @PathVariable String tripId) {
        if (passengerService.persist(passenger, tripId)) {
            notificationService.sendEmail(new EventDto(
                    new UserSmallDto(passenger.getUserId(),
                            compileFullName(passenger)),
                    EventType.PASSENGER_ADDED,
                    passenger.getProfile().getEmail()));
        }
    }

    private String compileFullName(Passenger passenger) {
        return passenger.getProfile().getName() + " " + passenger.getProfile().getSurname();
    }

    @PatchMapping("/{passengerId}/{tripId}")
    public ResponseEntity<String> setPassengerForTrip(@PathVariable String userId,
                                                      @PathVariable String tripId) {
        var passed = passengerService.assignPassengerToTrip(userId, tripId);
        return passed
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("Passenger %s not found", userId));

    }
}
