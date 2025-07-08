package ua.hudyma.telegramservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.hudyma.telegramservice.domain.TelegramUser;

import java.util.Optional;

public interface TelegramRepository extends MongoRepository <TelegramUser, Long> {
    Optional<TelegramUser> findByChatId(String chatId);
}
