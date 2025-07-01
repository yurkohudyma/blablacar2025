package ua.hudyma.bookingservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.hudyma.bookingservice.domain.TripPassenger;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripPassengerRepository extends MongoRepository<TripPassenger, String> {
    List<TripPassenger> findByPassengerId(String passengerId);
    List<TripPassenger> findByTripId(String tripId);
    Optional<TripPassenger> findByTripIdAndPassengerId(String tripId, String passengerId);
}

