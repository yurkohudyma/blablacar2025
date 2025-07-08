package ua.hudyma.telegramservice.dto;

public record EventDto(UserDto userDto, EventType eventType, String sendTo){
}
