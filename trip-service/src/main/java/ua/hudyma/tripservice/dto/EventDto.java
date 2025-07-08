package ua.hudyma.tripservice.dto;

public record EventDto(UserDto userDto, EventType eventType, String sendTo){
}
