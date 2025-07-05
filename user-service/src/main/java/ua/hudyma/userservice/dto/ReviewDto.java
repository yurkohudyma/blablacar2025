package ua.hudyma.userservice.dto;

import java.util.Date;

public record ReviewDto(Integer grade, String authorId, Date publishedOn, String reviewText) {
}
