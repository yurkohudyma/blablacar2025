package ua.hudyma.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.hudyma.tripservice.domain.Trip;

import java.util.List;

@FeignClient(name = "trip-service")
public interface TripClient {
    @GetMapping("/trips/getAllTrips/{driverId}")
    List<Trip> findAllByDriverId(@PathVariable("driverId") Long driverId);
}
