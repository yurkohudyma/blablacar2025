package ua.hudyma.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.hudyma.userservice.client.TripClient;
import ua.hudyma.userservice.domain.Profile;
import ua.hudyma.userservice.repository.DriverRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class DriverService {

    private final TripClient tripClient;
    private final DriverRepository driverRepository;

    /*public Optional<Profile> getTripByDriverId (Long driverId){
        return driverClient.getTripByDriverId(driverId);
    }*/

    public Profile getProfileByDriverId (Long driverId){
        var driver = driverRepository.findById(driverId).orElseThrow();
        return driver.getProfile();
    }

}
