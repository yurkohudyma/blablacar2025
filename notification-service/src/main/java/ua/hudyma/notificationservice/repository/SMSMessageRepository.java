package ua.hudyma.notificationservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.hudyma.notificationservice.domain.SMSMessage;

public interface SMSMessageRepository extends MongoRepository<SMSMessage, String> {
}
