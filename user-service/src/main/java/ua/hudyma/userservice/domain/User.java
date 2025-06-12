package ua.hudyma.userservice.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.OneToMany;
import ua.hudyma.tripservice.domain.Trip;

import java.util.ArrayList;
import java.util.List;

public abstract class User {

    @Embedded
    private Profile profile;

    // todo private Rating rating;
    //todo Balance balance;
    @OneToMany
    private List<Trip> trips = new ArrayList<>();


}
