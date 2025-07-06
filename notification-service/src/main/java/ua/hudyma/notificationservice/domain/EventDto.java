package ua.hudyma.notificationservice.domain;

import ua.hudyma.notificationservice.dto.UserDto;

public record EventDto(UserDto userDto, EventType eventType, String sendTo){
}
