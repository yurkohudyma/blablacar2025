package ua.hudyma.userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ua.hudyma.userservice.domain.Profile;
import ua.hudyma.userservice.service.UserService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Log4j2
public class UserController {
    private final UserService userService;
    @GetMapping("/profile/{userId}")
    public Optional<Profile> getProfile (@PathVariable Long userId){
        return userService.getProfileByUserId(userId);
    }
}
