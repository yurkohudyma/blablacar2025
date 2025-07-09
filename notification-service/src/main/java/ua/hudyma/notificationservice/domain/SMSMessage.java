package ua.hudyma.notificationservice.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sms_messages")
@Data
public class SMSMessage {

    @Id
    String id;

    public SMSMessage(String sid, String to, String from, String body) {
        this.sid = sid;
        this.to = to;
        this.from = from;
        this.body = body;
    }

    String sid;

    String to;

    String from;

    String body;
}
