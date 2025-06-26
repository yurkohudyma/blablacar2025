package ua.hudyma.tripservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.tripservice.domain.City;
import ua.hudyma.tripservice.service.CityService;

import java.util.Arrays;
import java.util.List;

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

    @GetMapping
    public List<City> getAllCities (){
        return cityService.getAllCities();
    }
}
