package ua.hudyma.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.hudyma.userservice.domain.Car;
import ua.hudyma.userservice.repository.CarRepository;
import ua.hudyma.userservice.repository.DriverRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class CarService {

    private final CarRepository carRepository;
    private final DriverRepository driverRepository;

    public Car findById(String id) {
        return carRepository.findById(id).orElse(new Car());
    }

    public void addCar(Car car, String userId) {
        var driver = driverRepository.findById(userId);
        if (driver.isPresent()) {
            car.setDriver(driver.get());
            carRepository.save(car);
        }
        else {
            log.error("Driver {} not found", userId);
        }
    }
}
