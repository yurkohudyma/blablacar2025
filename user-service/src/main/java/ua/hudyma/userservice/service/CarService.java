package ua.hudyma.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hudyma.userservice.domain.Car;
import ua.hudyma.userservice.repository.CarRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
}
