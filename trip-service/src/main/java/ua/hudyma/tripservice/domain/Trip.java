package ua.hudyma.tripservice.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "trips")
@Data
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

}
