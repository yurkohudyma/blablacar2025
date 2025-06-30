package ua.hudyma.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.hudyma.userservice.domain.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository <Passenger, Long> {

}
