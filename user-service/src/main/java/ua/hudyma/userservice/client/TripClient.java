package ua.hudyma.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;

import java.util.Optional;

@FeignClient(name = "trip-service")
public interface TripClient {

    /*@GetMapping("/drivers/{driverId}")
    Optional<Profile> getProfileByDriverId (@PathVariable Long driverId);*/

}
