package ua.hudyma.userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.hudyma.userservice.repository.DriverRepository;
import ua.hudyma.userservice.repository.PassengerRepository;
import ua.hudyma.userservice.service.DriverService;
import ua.hudyma.userservice.service.PassengerService;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/users")
public class UserController {

    private final DriverService driverService;
    private final PassengerService passengerService;

}
