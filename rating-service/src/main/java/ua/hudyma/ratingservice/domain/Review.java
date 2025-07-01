package ua.hudyma.ratingservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "reviews")
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String passengerId;
    @Column(nullable = false)
    String tripId;
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "rating_id")
    private Rating rating;
    @Column(nullable = false)
    private Integer grade;
    @Column(nullable = false)
    private String reviewText;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    @CreationTimestamp
    private Date publishedOn;
}
