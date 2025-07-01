package ua.hudyma.bookingservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.hudyma.bookingservice.domain.TripPassenger;
import ua.hudyma.bookingservice.dto.TripDto;
import ua.hudyma.bookingservice.repository.TripPassengerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class TripPassengerService {

    private final TripPassengerRepository tripPassengerRepository;

    public TripPassenger addEntry(TripPassenger tripPassenger) {
        return tripPassengerRepository.save(tripPassenger);
    }

    public TripPassenger addEntry(TripDto tripDto) {
        var tripPassenger = new TripPassenger(
                tripDto.passengerId(), tripDto.tripId());
        return tripPassengerRepository.save(tripPassenger);
    }

    public List<TripPassenger> getAllTripPassByTripId(String tripId) {
        return tripPassengerRepository.findByTripId(tripId);
    }

    public boolean deleteEntry(String objectId) {
        var entry = tripPassengerRepository
                .findById(objectId);
        if (entry.isPresent()) {
            tripPassengerRepository.delete(entry.get());
            return true;
        } else {
            log.error
                    ("entry {} not found", objectId);
            return false;
        }
    }
}
