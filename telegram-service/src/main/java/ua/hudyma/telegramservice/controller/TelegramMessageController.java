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
import ua.hudyma.telegramservice.service.TelegramBotService;
import ua.hudyma.telegramservice.service.TelegramService;

import java.util.Map;

@RestController
@RequestMapping("/telegram")
@RequiredArgsConstructor
@Log4j2
public class TelegramMessageController {
    private final TelegramBotService telegramBotService;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody Map<String, String> payload) {
        return telegramBotService.sendMessage(payload);
    }
}
