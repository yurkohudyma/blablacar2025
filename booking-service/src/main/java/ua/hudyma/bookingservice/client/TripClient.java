package ua.hudyma.bookingservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "trip-service")
public interface TripClient {
}
