package ua.hudyma.userservice.dto;

import java.util.Date;

public record ReviewDto(Integer grade, Date publishedOn, String reviewText) {
}
