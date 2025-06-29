package ua.hudyma.bookingservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.bookingservice.domain.TripPassenger;
import ua.hudyma.bookingservice.dto.TripDto;
import ua.hudyma.bookingservice.service.TripPassengerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/trips-passengers")
public class TripPassengerController {
    private final TripPassengerService tripPassengerService;

    @PostMapping
    public TripPassenger addEntry (@RequestBody TripPassenger tripPassenger){
        return tripPassengerService.addEntry (tripPassenger);
    }

    @PostMapping("/dto")
    public TripDto addEntry(@RequestBody TripDto tripDto) {
        TripPassenger saved = tripPassengerService.addEntry(tripDto);
        return new TripDto( saved.getPassengerId(), tripDto.tripId());
    }

    @GetMapping("/{tripId}")
    public List<TripPassenger> getTripPasByTripId (@PathVariable String tripId){
        return tripPassengerService.getAllTripPassByTripId (tripId);
    }
}
