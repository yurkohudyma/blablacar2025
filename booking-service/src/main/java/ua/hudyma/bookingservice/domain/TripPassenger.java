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
    String userId;
    @Indexed
    String tripId;

    public TripPassenger(String userId, String tripId) {
        this.userId = userId;
        this.tripId = tripId;
    }
}
