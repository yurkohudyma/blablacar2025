package ua.hudyma.userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.userservice.domain.Car;
import ua.hudyma.userservice.repository.CarRepository;
import ua.hudyma.userservice.repository.DriverRepository;
import ua.hudyma.userservice.service.CarService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/cars")
public class CarController {

    private final CarRepository carRepository;
    private final DriverRepository driverRepository;
    private final CarService carService;

    @GetMapping("/{id}")
    public Car getCarById (@PathVariable String id){
        return carRepository.findById(id).orElseThrow();
    }

    /*@GetMapping("/getCars/{driverId}")
    public List<Car> getDriversCarList (@PathVariable String driverId){
        return carService.getCarListByDriverId(driverId);
    }*/

    @PostMapping("/{userId}")
    public void addCar (@RequestBody Car car, @PathVariable String userId){
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
