package ua.hudyma.telegramservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.hudyma.telegramservice.domain.Message;
import ua.hudyma.telegramservice.domain.TelegramUser;
import ua.hudyma.telegramservice.repository.MessageRepository;
import ua.hudyma.telegramservice.repository.TelegramRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class TelegramService {
    private final TelegramRepository telegramRepository;
    private final MessageRepository messageRepository;

    public void persistUser(TelegramUser user) {
        var tlgUser = telegramRepository.findByChatId(user.getChatId()); 
        if (tlgUser.isEmpty()) {
            telegramRepository.save(user);
            log.info("---- saved user chatId = {}", user.getChatId());            
        } else if (user.getName() != null &&
                !user.getName().isEmpty() &&
                tlgUser.get().getName() == null ||
                tlgUser.get().getName().isEmpty()) {
            tlgUser.get().setName(user.getName());
            telegramRepository.save(tlgUser.get());
            log.info("username {} for chatId = {} discovered, updating", user.getName(), user.getChatId());
        } else {
            log.error("telegram user with chatId = {} exists, no persist", user.getChatId());
        }
    }

    public void persistMessage (Message message){
        messageRepository.save(message);
        log.info("message from {} saved", message.getChatId());
    }
}
