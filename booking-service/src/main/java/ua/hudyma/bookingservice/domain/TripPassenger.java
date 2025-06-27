package ua.hudyma.bookingservice.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trips_passengers")
@Data
public class TripPassenger {

    @Id
    String id;
    @Indexed
    Long passengerId;
    @Indexed
    String tripId;
}
