package ua.hudyma.telegramservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.hudyma.telegramservice.service.TelegramBot;

import java.util.Map; // Для простого прикладу з Map в @RequestBody

@RestController
@RequestMapping("/telegram")
@RequiredArgsConstructor
@Log4j2
public class TelegramMessageController {
    private final TelegramBot telegramBot;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody Map<String, String> payload) {
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
            return ResponseEntity.ok("Повідомлення успішно відправлено!");
        } catch (TelegramApiException e) {
            log.error("Помилка відправки повідомлення: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Не вдалося відправити повідомлення: " + e.getMessage());
        }
    }
}
