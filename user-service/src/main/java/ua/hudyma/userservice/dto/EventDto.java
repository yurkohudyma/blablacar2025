package ua.hudyma.userservice.dto;

import ua.hudyma.userservice.domain.EventType;

public record EventDto (UserSmallDto userDto, EventType eventType, String sendTo) {
}
