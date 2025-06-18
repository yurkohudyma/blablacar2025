package ua.hudyma.userservice.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "drivers")
@Data
public class Driver extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

}
