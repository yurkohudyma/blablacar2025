package ua.hudyma.ratingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.hudyma.ratingservice.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
