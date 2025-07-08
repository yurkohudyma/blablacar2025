package ua.hudyma.telegramservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.hudyma.telegramservice.domain.Message;
import ua.hudyma.telegramservice.repository.MessageRepository;

import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class TelegramBotService {

    private final TelegramBot telegramBot;
    private final MessageRepository messageRepository;

    public ResponseEntity<String> sendMessage (Map<String, String> payload){
        String chatId = payload.get("chatId");
        String messageText = payload.get("messageText");
        if (chatId == null || messageText == null || chatId.isEmpty() || messageText.isEmpty()) {
            return ResponseEntity.badRequest().body("Поля 'chatId' та 'messageText' є обов'язковими.");
        }
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        try {
            telegramBot.execute(message);
            messageRepository.save(new Message(message.getChatId(), message.getText()));
            return ResponseEntity.ok("Повідомлення успішно відправлено!");
        } catch (TelegramApiException e) {
            log.error("Помилка відправки повідомлення: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Не вдалося відправити повідомлення: " + e.getMessage());
        }
    }
}
