package ua.hudyma.notificationservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.hudyma.notificationservice.domain.SMSMessage;
import ua.hudyma.notificationservice.service.SMS_Service;

@RestController
@RequestMapping("/sms")
@RequiredArgsConstructor
@Log4j2 public class SMS_Controller {

    private final SMS_Service smsService;

    @PostMapping
    public ResponseEntity<String> sendSMS (@RequestBody SMSMessage message){
        smsService.sendSms(message.getTo(), message.getBody());
        return ResponseEntity.ok("Відправлено успішно");
    }
}
