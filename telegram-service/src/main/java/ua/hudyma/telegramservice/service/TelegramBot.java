package ua.hudyma.telegramservice.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.hudyma.telegramservice.domain.Message;
import ua.hudyma.telegramservice.domain.TelegramUser;

@Component
@Log4j2
@Getter
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final TelegramService telegramService;

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();

            long chatId = update.getMessage().getChatId();
            telegramService.persistMessage(new Message(String.valueOf(chatId), messageText));
            String responseText = "Отримав твоє повідомлення: '" + messageText + "'";

            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(responseText);

            try {
                execute(message);
                var user = update.getMessage().getFrom();
                var chatIdStr = String.valueOf(chatId);
                var tlgUser = new TelegramUser();
                if (user != null){
                    var userName = user.getUserName();
                    if (userName != null && !userName.isEmpty()){
                        tlgUser.setName(userName);
                    }
                }
                tlgUser.setChatId(chatIdStr);
                telegramService.persistUser(tlgUser);

            } catch (TelegramApiException e) {
                log.error(e);
            }
        }
    }

    private Message convertToMessage(SendMessage sendMessage) {
        return new Message(sendMessage.getChatId(), sendMessage.getText());
    }
}
