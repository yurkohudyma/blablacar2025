package ua.hudyma.tripservice.service;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hudyma.tripservice.domain.City;
import ua.hudyma.tripservice.repository.CityRepository;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public void persistCity(City city) {
        init(city);
        cityRepository.save(city);
    }

    private void init(City city) {
        if (city.getId() == null || city.getId().isEmpty()) {
            Random random = new SecureRandom();
            city.setId(NanoIdUtils.randomNanoId(random, NanoIdUtils.DEFAULT_ALPHABET, 4));
        }
    }

    public Optional<City> getCityById(String id) {
        return cityRepository.findById(id);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
