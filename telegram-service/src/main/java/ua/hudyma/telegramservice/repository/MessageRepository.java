package ua.hudyma.telegramservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.hudyma.telegramservice.domain.Message;

public interface MessageRepository extends MongoRepository<Message, String> {
}
