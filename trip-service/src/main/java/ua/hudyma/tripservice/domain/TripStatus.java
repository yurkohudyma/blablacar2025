package ua.hudyma.tripservice.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TripStatus {
    WAITING_CONFIRMATION("Очікує підтвердження"),
    CONFIRMED("Підтверджено"),
    CANCELLED("Скасовано"),
    COMPLETED("Завершено"),
    IN_PROGRESS("У процесі"),
    EXPIRED("Протерміновано");

    private final String value;

    TripStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public static TripStatus fromValue(String value) {
        for (TripStatus status : TripStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }

}

