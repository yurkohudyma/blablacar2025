package ua.hudyma.tripservice.domain;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "cities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    private String id;
    @Column(unique = true, nullable = false)
    private String name;
    private String country;
    private String region;
    private double latitude;
    private double longitude;
    private boolean isActive;
    private int population;
    private String timezone;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "city_nearby",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "nearby_city_id")
    )
    private List<City> nearbyCities;
}
