package ua.hudyma.userservice.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User {

    @Embedded
    private Profile profile;

    // todo private Rating rating;
    //todo Balance balance;
   /* @OneToMany
    private List<Trip> trips = new ArrayList<>();*/


}
