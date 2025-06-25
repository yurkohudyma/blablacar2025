package ua.hudyma.tripservice.domain;

import jakarta.persistence.*;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import lombok.Data;
import ua.hudyma.tripservice.service.DistanceService;
import ua.hudyma.tripservice.util.DistanceCalculator;

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

    @Column
    Double directDistance;

    @Column
    Double optimalDistance;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private City destination;

    @ManyToOne
    @JoinColumn(name = "departure_id", nullable = false)
    private City departure;

    @PrePersist
    public void beforeSave (){
        generateId();
        calculateDirectDistance();
        calculateOptimalDistance();
    }

    private void calculateOptimalDistance() {
        if (this.optimalDistance == null || this.optimalDistance == 0d){
            optimalDistance = DistanceService.getDistance(departure, destination);
        }
    }

    public void generateId() {
        if (this.id == null || this.id.isEmpty()) {
            Random random = new SecureRandom();
            this.id = NanoIdUtils.randomNanoId(random, NanoIdUtils.DEFAULT_ALPHABET, 16);
        }
    }

    public void calculateDirectDistance (){
        if (this.directDistance == null || this.directDistance == 0d){
            directDistance =
                    DistanceCalculator.haversine(destination, departure);
        }
    }

    //todo introduce passenger for trip
    // private List<Passenger> passengerList = new ArrayList<>();

}
