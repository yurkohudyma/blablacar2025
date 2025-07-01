package ua.hudyma.ratingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.hudyma.ratingservice.domain.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    boolean existsByUserId(String userId);
}
