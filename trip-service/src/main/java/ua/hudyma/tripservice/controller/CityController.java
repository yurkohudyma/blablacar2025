package ua.hudyma.tripservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<String> addAllCity(@RequestBody City[] cities) {
        Arrays.stream(cities)
              .forEach(cityService::persistCity);
        return ResponseEntity.ok().body("Added all");
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping("/role")
    public String roleProtectedEndpoint() {
        return "Hello from role protected endpoint!";
    }

    @GetMapping
    public List<City> getAllCities (){
        return cityService.getAllCities();
    }
}
