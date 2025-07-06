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

    private final CarService carService;

    @GetMapping("/{id}")
    public Car getCarById (@PathVariable String id){
        return carService.findById(id);
    }

    @PostMapping("/{userId}")
    public void addCar (@RequestBody Car car, @PathVariable String userId){
        carService.addCar(car, userId);
    }
}
