package ua.hudyma.notificationservice.service;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.hudyma.notificationservice.domain.SMSMessage;
import ua.hudyma.notificationservice.repository.SMSMessageRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class SMS_Service {

    private final SMSMessageRepository smsMessageRepository;

    @Value("${twilio.account.sid}")
    private String accountSID;

    @Value("${twilio.token}")
    private String token;

    @Value("${twilio.phonenumber}")
    private String phonenumber;

    @PostConstruct
    private void initTwilio() {
        Twilio.init(accountSID, token);
        log.info("Twilio initialized with SID = {}", accountSID);
    }

    public void sendSms(String to, String body) {
        Message message = Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(phonenumber),
                body
        ).create();
        log.info("message sid = {}", message.getSid());
        smsMessageRepository.save(new SMSMessage(
                message.getSid(),
                message.getTo(),
                message.getFrom().toString(),
                message.getBody()
        ));
    }
}

