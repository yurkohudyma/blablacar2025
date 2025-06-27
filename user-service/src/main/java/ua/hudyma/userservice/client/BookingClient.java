package ua.hudyma.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.hudyma.userservice.dto.TripDto;

import java.util.List;

@FeignClient(name = "booking-service")
public interface BookingClient {

    @GetMapping("/trips-passengers/{tripId}")
    List<TripDto> findAllByTripId (@PathVariable String tripId);

}
