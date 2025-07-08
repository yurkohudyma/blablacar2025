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

    public void persistUser(TelegramUser newUser) {
        telegramRepository.findByChatId(newUser.getChatId())
                .ifPresentOrElse(existingUser -> {
            boolean existingNameEmpty = existingUser.getName() == null ||
                    existingUser.getName().isEmpty();
            boolean newNameAvailable = newUser.getName() != null &&
                    !newUser.getName().isEmpty();

            if (existingNameEmpty && newNameAvailable) {
                existingUser.setName(newUser.getName());
                telegramRepository.save(existingUser);
                log.info("Username '{}' for chatId = {} discovered, updating",
                        newUser.getName(), newUser.getChatId());
            } else {
                log.info("Telegram user [{} / {}] exists, no update needed",
                        newUser.getChatId(), existingUser.getName());
            }
        }, () -> {
            telegramRepository.save(newUser);
            log.info("Saved new user with chatId = {}", newUser.getChatId());
        });
    }

    public void persistMessage(Message message) {
        messageRepository.save(message);
        log.info("message from {} saved", message.getChatId());
    }
}
