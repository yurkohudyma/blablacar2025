package ua.hudyma.telegramservice.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
@Data
public class Message {

    @Id
    String id;

    @Indexed
    String chatId;

    @Indexed
    String text;


    public Message(String chatId, String text) {
        this.chatId = chatId;
        this.text = text;
    }
}
