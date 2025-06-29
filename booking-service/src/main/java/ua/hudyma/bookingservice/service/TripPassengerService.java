package ua.hudyma.bookingservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import ua.hudyma.bookingservice.client.TripClient;
import ua.hudyma.bookingservice.domain.TripPassenger;
import ua.hudyma.bookingservice.dto.TripDto;
import ua.hudyma.bookingservice.repository.TripPassengerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class TripPassengerService {

    private final TripClient tripClient;
    private final TripPassengerRepository tripPassengerRepository;

    public TripPassenger addEntry(TripPassenger tripPassenger) {
        return tripPassengerRepository.save(tripPassenger);
    }

    //@PostMapping
    public TripPassenger addEntry(TripDto tripDto) {
        var tripPassenger = new TripPassenger(
                tripDto.passengerId(), tripDto.tripId());
        return tripPassengerRepository.save(tripPassenger);
    }

    public List<TripPassenger> getAllTripPassByTripId(String tripId) {
        return tripPassengerRepository.findByTripId(tripId);
    }
}
