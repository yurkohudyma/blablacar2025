package ua.hudyma.userservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table (name = "drivers")
@Data
public class Driver extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String tripId;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    List<Car> carList = new ArrayList<>();

}
