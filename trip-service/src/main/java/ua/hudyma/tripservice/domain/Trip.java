package ua.hudyma.tripservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "trips")
@Data
public class Trip {

    @Id
    private String id;

    @Column(nullable = false)
    String driverId;

    @Column(nullable = false)
    String carId;

    @Column
    Double directDistance;

    @Column
    Double optimalDistance;

    @Column
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    LocalDateTime tripCreated;

    @Column
    @JsonFormat(pattern = "HH:mm dd-MM-yyyy")
    LocalDateTime tripAssigned;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private City destination;

    @ManyToOne
    @JoinColumn(name = "departure_id", nullable = false)
    private City departure;

    @Enumerated(value = EnumType.STRING)
    TripStatus status;

    Integer price;
}
