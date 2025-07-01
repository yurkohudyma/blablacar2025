package ua.hudyma.userservice.domain;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "drivers")
@Data
public class Driver extends User {

    @Id
    String userId;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    List<Car> carList = new ArrayList<>();

    Integer drivingSkills;

    @PrePersist
    public void generateId() {
        if (this.userId == null || this.userId.isEmpty()) {
            Random random = new SecureRandom();
            this.userId = NanoIdUtils.randomNanoId(random, NanoIdUtils.DEFAULT_ALPHABET, 6);
        }
    }

}
