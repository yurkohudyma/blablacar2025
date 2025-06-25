package ua.hudyma.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.hudyma.userservice.domain.Passenger;
import ua.hudyma.userservice.repository.PassengerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassengerService {
    private final PassengerRepository passengerRepository;

    public List<Passenger> getPassengerListByTripId(String tripId) {
        return passengerRepository.findAllByTripId(tripId);
    }

    public void persist(Passenger passenger, String tripId) {
        passenger.setTripId(tripId);
        passengerRepository.save(passenger);
    }

    public Optional<Passenger> getById(Long passengerId) {
        return passengerRepository.findById(passengerId);
    }
}
