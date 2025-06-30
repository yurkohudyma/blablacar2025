package ua.hudyma.userservice.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class User {

    @Embedded
    private Profile profile;

    private Long tripQuantity;

    //todo зробити окремий rating-service

}
