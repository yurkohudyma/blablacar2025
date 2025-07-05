package ua.hudyma.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.hudyma.userservice.client.BookingClient;
import ua.hudyma.userservice.client.TripClient;
import ua.hudyma.userservice.domain.ExperienceLevel;
import ua.hudyma.userservice.domain.Passenger;
import ua.hudyma.userservice.dto.TripPassengerDto;
import ua.hudyma.userservice.repository.PassengerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class PassengerService {
    private final TripClient tripClient;
    private final BookingClient bookingClient;
    private final PassengerRepository passengerRepository;
    private final DriverService driverService;

    public List<Passenger> getPassengerListByTripId(String tripId) {
        var dtoList = bookingClient.findAllByTripId(tripId);
        return dtoList.stream()
                .map(dto -> passengerRepository
                        .findById(dto.passengerId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public void persist(Passenger passenger, String tripId) {
        var list = bookingClient.findAllByTripId(tripId);
        var passIdAllreadyBoundWithTrip =
                list.stream()
                        .map(TripPassengerDto::passengerId)
                        .anyMatch(e -> e.equals(passenger.getUserId()));
        if (passIdAllreadyBoundWithTrip) {
            log.error("passenger {} already bound with trip {}",
                    passenger.getUserId(), tripId);
        } else {
            passenger.setExpLevel(ExperienceLevel.NEWCOMER);
            var tripQty = passenger.getTripQuantity();
            tripQty = tripQty == null ? 0L : tripQty + 1L;
            passenger.setTripQuantity(tripQty);
            passengerRepository.save(passenger);
            bookingClient.createTripPassengerBinding(
                    new TripPassengerDto(passenger.getUserId(), tripId));
        }
    }

    public Optional<Passenger> getById(String userId) {
        return passengerRepository.findById(userId);
    }

    public boolean checkIfExists(String tripId) {
        driverService.checkEureka();
        try {
            return tripClient.existsById(tripId);
        } catch (Exception e) {
            log.error("Failed to fetch trip {}: {}",
                    tripId, e.getMessage());
        }
        log.error("error finding trip {}", tripId);
        return false;
    }

    public boolean assignPassengerToTrip(String userId, String tripId) {
        try {
            Optional<Passenger> passengerOpt = getById(userId);
            if (passengerOpt.isPresent()) {
                persist(passengerOpt.get(), tripId);
                return true;
            } else {
                log.error("Passenger {} not found", userId);
                return false;
            }
        } catch (Exception e) {
            log.error("Exception while assigning passenger", e);
            return false;
        }
    }

}

