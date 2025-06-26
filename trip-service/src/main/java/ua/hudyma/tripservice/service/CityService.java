package ua.hudyma.tripservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hudyma.tripservice.domain.City;
import ua.hudyma.tripservice.repository.CityRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    public void persistCity(City city) {
        cityRepository.save(city);
    }

    public Optional<City> getCityById(String id) {
        return cityRepository.findById(id);

    }
}
