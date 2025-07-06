package ua.hudyma.userservice.dto;

import ua.hudyma.userservice.domain.EventType;

public record EventDto (UserDto userDto, EventType eventType, String sendTo) {
}
