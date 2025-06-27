package ua.hudyma.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import ua.hudyma.userservice.client.BookingClient;
import ua.hudyma.userservice.client.TripClient;
import ua.hudyma.userservice.domain.Passenger;
import ua.hudyma.userservice.dto.TripDto;
import ua.hudyma.userservice.repository.PassengerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
@RequiredArgsConstructor
@Log4j2
public class PassengerService {
    private final TripClient tripClient;
    private final BookingClient bookingClient;
    private final PassengerRepository passengerRepository;
    private final DriverService driverService;

    public List<Passenger> getPassengerListByTripId(String tripId) {
        return passengerRepository.findAllByTripId(tripId);
    }

    public void persist(Passenger passenger, String tripId) {
        //passenger.setTripId(tripId);
        var list = bookingClient.findAllByTripId(tripId);
        var passIdAllreadyBoundWithTrip =
                list.stream()
                        .map(TripDto::passengerId)
                        .anyMatch(e -> e.equals(passenger.getId()));
        if (passIdAllreadyBoundWithTrip){
            log.error("passenger {} already bound with trip {}", passenger.getId(), tripId);
        }
        passengerRepository.save(passenger);
    }

    public Optional<Passenger> getById(Long passengerId) {
        return passengerRepository.findById(passengerId);
    }

    @GetMapping
    public boolean checkIfExists(String tripId) {
        driverService.checkEureka();
        try {
            return tripClient.existsById(tripId);
        } catch (Exception e) {
            log.error("Failed to fetch trip {}: {}", tripId, e.getMessage());
        }
        log.error("error finding trip {}", tripId);
        return false;
    }
}
