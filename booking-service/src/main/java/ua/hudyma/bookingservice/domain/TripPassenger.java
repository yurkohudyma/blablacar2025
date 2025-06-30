package ua.hudyma.bookingservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trips_passengers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripPassenger {

    @Id
    String id;
    @Indexed
    Long passengerId;
    @Indexed
    String tripId;

    public TripPassenger(Long passengerId, String tripId) {
        this.passengerId = passengerId;
        this.tripId = tripId;
    }
}
