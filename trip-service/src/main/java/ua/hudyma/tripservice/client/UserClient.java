package ua.hudyma.tripservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.tripservice.dto.DriverDto;

import java.util.Optional;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/drivers/exists/{userId}")
    boolean existsById (@PathVariable String userId);
    @GetMapping("/drivers/{userId}")
    Optional<DriverDto> findById(@PathVariable String userId);
    @PostMapping("/drivers/updateTripQty/{userId}")
    void setUserTripQtyToZeroOrIncrement(@PathVariable String userId);
}
