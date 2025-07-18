package ua.hudyma.telegramservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.data.mongodb.config.EnableMongoAuditing;

//@EnableDiscoveryClient
@SpringBootApplication
//@EnableFeignClients
@EnableMongoAuditing
public class TelegramServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                TelegramServiceApplication.class, args);
    }
}
