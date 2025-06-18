package ua.hudyma.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.hudyma.userservice.client.UsersClient;
import ua.hudyma.userservice.domain.Profile;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {

    private final UsersClient usersClient;

    public Optional<Profile> getProfileByUserId (Long userId){
        return usersClient.getProfileByUserId(userId);
    }
}
