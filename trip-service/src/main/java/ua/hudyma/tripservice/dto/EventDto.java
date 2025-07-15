package ua.hudyma.tripservice.dto;

public record EventDto(UserSmallDto userDto, EventType eventType, String sendTo){
}
