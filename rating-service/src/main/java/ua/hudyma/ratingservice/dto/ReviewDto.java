package ua.hudyma.ratingservice.dto;

import java.util.Date;

public record ReviewDto(Integer grade, String authorId, Date publishedOn, String reviewText) {
}
