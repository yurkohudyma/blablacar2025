package ua.hudyma.telegramservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.hudyma.telegramservice.dto.EventDto;
import ua.hudyma.telegramservice.service.TelegramBotService;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Consumer;


@Configuration
@Log4j2
@RequiredArgsConstructor
public class RabbitConfig {

    private final TelegramBotService telegramBotService;

    @Bean
    public Consumer<byte[]> tripCreatedEventSentConsumer(ObjectMapper objectMapper) {
        return bytes -> {
            String raw = new String(bytes, StandardCharsets.UTF_8);
            log.info("RAW payload: {}", raw);
            try {
                EventDto event = objectMapper.readValue(raw, EventDto.class);
                log.info("Parsed event: {}", event);
                telegramBotService.sendMessage(Map.of(
                        "chatId", event.sendTo(),
                        "messageText", event.eventType().toString() + "/" + event.userDto().userId() + "/" + event.userDto().username()));
            } catch (Exception e) {
                log.error("Failed to parse EventDto", e);
            }
        };
    }


}
