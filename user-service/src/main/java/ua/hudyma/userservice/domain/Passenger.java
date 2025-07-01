package ua.hudyma.userservice.domain;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.security.SecureRandom;
import java.util.Random;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "passengers")
@Data
public class Passenger extends User {

    @Id
    String userId;

    @PrePersist
    public void generateId() {
        if (this.userId == null || this.userId.isEmpty()) {
            Random random = new SecureRandom();
            this.userId = NanoIdUtils.randomNanoId(
                    random, NanoIdUtils.DEFAULT_ALPHABET, 6);
        }
    }
}
