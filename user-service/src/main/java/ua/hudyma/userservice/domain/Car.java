package ua.hudyma.userservice.domain;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.util.Random;

@Entity
@Table (name = "cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    Color color;

    String model;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @PrePersist
    public void generateId() {
        if (this.id == null || this.id.isEmpty()) {
            Random random = new SecureRandom();
            this.id = NanoIdUtils.randomNanoId(random, NanoIdUtils.DEFAULT_ALPHABET, 8);
        }
    }

    enum Color {
        RED("Червоний"),
        GREEN("Зелений"),
        WHITE("Білий"),
        BLACK("Чорний"),
        GREY("Сірий"),
        YELLOW("Жовтий"),
        BLUE("Синій"),
        BROWN("Коричневий"),
        ORANGE("Помаранчевий");

        public final String str;

        Color(String str) {
            this.str = str;
        }
    }

}
