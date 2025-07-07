import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.hudyma.userservice.controller.PassengerController;
import ua.hudyma.userservice.domain.Passenger;
import ua.hudyma.userservice.service.PassengerService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PassengerControllerTest {

    @Mock
    private PassengerService passengerService;

    @InjectMocks
    private PassengerController passengerController;

    @Test
    void shouldReturnPassengersByTripId() {
        String tripId = "trip-001";

        Passenger passenger = new Passenger();
        passenger.setUserId("ABC123");

        when(passengerService.getPassengerListByTripId(tripId))
                .thenReturn(List.of(passenger));

        List<Passenger> result = passengerController.getPassengersListByTripId(tripId);

        assertEquals(1, result.size());
        assertEquals("ABC123", result.get(0).getUserId());
    }
}
