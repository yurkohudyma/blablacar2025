package ua.hudyma.userservice.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "passengers")
@Data
public class Passenger extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String tripId;
}
