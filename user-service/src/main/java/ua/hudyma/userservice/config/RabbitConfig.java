package ua.hudyma.userservice.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.hudyma.userservice.dto.EventDto;

import java.util.function.Consumer;

@Configuration
@Log4j2
public class RabbitConfig {

    @Bean
    public Consumer<EventDto> emailEventSentConsumer (){
        return event -> {
            log.info("---- received event {}", event);
            //to something useful
        };
    }


}
