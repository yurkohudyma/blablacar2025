package ua.hudyma.userservice.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ExperienceLevel {

    NEWCOMER("Новачок"),
    INTERMEDIATE("Впевнений"),
    EXPERIENCED("Досвідчений"),
    EXPERT("Експерт"),
    AMBASSADOR("Амбасадор");

    private final String value;

    ExperienceLevel(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public static ExperienceLevel fromValue(String value) {
        for (ExperienceLevel status : ExperienceLevel.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }
}
