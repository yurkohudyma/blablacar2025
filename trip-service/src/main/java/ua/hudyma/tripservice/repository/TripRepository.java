package ua.hudyma.tripservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.hudyma.tripservice.domain.Trip;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository <Trip, String> {
    List<Trip> findAllByDriverId(Long driverId);
}
