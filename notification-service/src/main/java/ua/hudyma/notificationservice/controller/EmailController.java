package ua.hudyma.notificationservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.notificationservice.dto.EventDto;
import ua.hudyma.notificationservice.service.EmailService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;
    private final StreamBridge streamBridge;

    @PostMapping
    public void sendEmail (@RequestBody EventDto event) throws IOException {
        emailService.sendEmail(event);
        streamBridge.send("email-topic", event);
        log.info("----- email event for {} shared to bridge", event.sendTo());
    }
}
