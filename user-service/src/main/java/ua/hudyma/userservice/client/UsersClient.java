package ua.hudyma.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ua.hudyma.userservice.domain.Profile;

import java.util.Optional;

@FeignClient(name = "users")
public interface UsersClient {

    @GetMapping("/users")
    Optional<Profile> getProfileByUserId (Long userId);

}
