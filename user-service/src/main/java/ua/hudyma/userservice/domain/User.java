package ua.hudyma.userservice.domain;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import jakarta.persistence.*;
import lombok.Data;

import java.security.SecureRandom;
import java.util.Random;

@Data
@MappedSuperclass
public abstract class User {

    @Embedded
    private Profile profile;
    private Long tripQuantity;
    @Enumerated(value = EnumType.STRING)
    private ExperienceLevel expLevel;
    private Boolean verifiedId;
    private Boolean confirmedEmail;
    private Boolean confirmedPhoneNumber;
    private Boolean professionalMember;
}
