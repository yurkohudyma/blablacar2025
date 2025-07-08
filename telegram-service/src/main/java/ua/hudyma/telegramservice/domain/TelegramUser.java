package ua.hudyma.telegramservice.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "telegram_data")
@Data
public class TelegramUser {

    @Id
    String id;

    @Indexed
    String chatId;

    String name;

    public TelegramUser() {
    }

    public TelegramUser(String chatId) {
        this.chatId = chatId;
    }

    public TelegramUser(String chatId, String name) {
        this.chatId = chatId;
        this.name = name;
    }
}