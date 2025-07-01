package ua.hudyma.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.userservice.domain.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, String> {
    //List<Car> findAllByDriverId(String userId);
}
