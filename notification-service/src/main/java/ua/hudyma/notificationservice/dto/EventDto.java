package ua.hudyma.notificationservice.dto;

public record EventDto(UserDto userDto, EventType eventType, String sendTo){
}
