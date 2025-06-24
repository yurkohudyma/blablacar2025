package ua.hudyma.tripservice.domain;

import jakarta.persistence.*;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import lombok.Data;

import java.security.SecureRandom;
import java.util.Random;

@Entity
@Table(name = "trips")
@Data
public class Trip {

    @Id
    private String id;

    @Column(nullable = false)
    Long driverId;

    @Column(nullable = false)
    String carId;
    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private City destination;

    @ManyToOne
    @JoinColumn(name = "departure_id", nullable = false)
    private City departure;

    @PrePersist
    public void generateId() {
        if (this.id == null || this.id.isEmpty()) {
            Random random = new SecureRandom();
            this.id = NanoIdUtils.randomNanoId(random, NanoIdUtils.DEFAULT_ALPHABET, 16);
        }
    }

    //todo introduce passenger for trip
    // private List<Passenger> passengerList = new ArrayList<>();

}
