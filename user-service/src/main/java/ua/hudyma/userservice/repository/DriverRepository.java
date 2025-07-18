package ua.hudyma.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.hudyma.userservice.domain.Driver;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, String> {
    Optional<Driver> findByUserId(String tripId);
}
