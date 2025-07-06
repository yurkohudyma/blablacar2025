package ua.hudyma.notificationservice.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.hudyma.notificationservice.domain.EventDto;
import ua.hudyma.notificationservice.exception.EmailNotSentException;

import java.io.IOException;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final EmailTemplateBuilder emailTemplateBuilder;
    @Value("${spring.mail.username}")
    private String fromEmail;
    static final String SUBJECT = "BlaBlaCar Notification";

    public void sendEmail (EventDto event) throws IOException, EmailNotSentException {
        Map<String, Object> model = Map.of(
                "addressee", event.sendTo(),
                "subject", SUBJECT,
                "event", event.eventType(),
                "user", event.userDto());
        generateHtmlContentAndSend(model, event.sendTo());
    }

    private void generateHtmlContentAndSend(Map<String, Object> model, String sendTo) throws
            IOException, EmailNotSentException {
        var htmlContent = emailTemplateBuilder.buildTemplate("email", model);
        var message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(
                    message,
                    true,
                    "UTF-8");
            helper.setTo(sendTo);
            helper.setSubject(SUBJECT);
            helper.setText(htmlContent, true);
            helper.setFrom(fromEmail);
            mailSender.send(message);
            log.info("-------- mail has been successfully sent to {}", sendTo);
        } catch (MessagingException e) {
            log.error("unable to send email to {}", sendTo);
            throw new EmailNotSentException("Не вдалося надіслати email");
        }

    }
}
