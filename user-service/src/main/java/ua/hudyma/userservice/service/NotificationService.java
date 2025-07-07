package ua.hudyma.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.hudyma.userservice.dto.EventDto;

@Service
@RequiredArgsConstructor
@Log4j2
public class NotificationService {

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;
    private final StreamBridge streamBridge;

    public void sendEmail(EventDto eventDto) {
        var instance = discoveryClient
                .getInstances("notification-service")
                .get(0);
        var uri = instance.getUri() + "/email";
        restTemplate.postForObject(uri, eventDto, Void.class);
    }
}
