package ua.hudyma.telegramservice.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "messages")
@Data
public class Message {

    @Id
    String id;

    @Indexed
    String chatId;

    @Indexed
    String text;

    @CreatedDate
    LocalDateTime timestamp;

    public Message(String chatId, String text) {
        this.chatId = chatId;
        this.text = text;
    }
}
