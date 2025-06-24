package ua.hudyma.tripservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.hudyma.tripservice.domain.City;
import ua.hudyma.tripservice.service.CityService;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    //todo implement adhering nearby cities

    @PostMapping("/addAll")
    public void addAllCity(@RequestBody City[] cities) {
        Arrays.stream(cities)
              .forEach(cityService::persistCity);
    }
}
