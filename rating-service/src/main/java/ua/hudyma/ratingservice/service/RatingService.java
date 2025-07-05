package ua.hudyma.ratingservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.hudyma.ratingservice.domain.Rating;
import ua.hudyma.ratingservice.repository.RatingRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class RatingService {

    private final RatingRepository ratingRepository;

    public void addRating(Rating rating) {
        if (rating.getUserId() != null && ratingRepository.existsByUserId(rating.getUserId())) {
            ratingRepository.save(rating);
        } else {
            log.error("rating with userId = {} exists", rating.getUserId());
        }
    }

    public Optional<Rating> getReviewById(Long ratingId) {
        return ratingRepository
                .findById(ratingId);
    }

    public Rating getById(Long ratingId) {
        return ratingRepository.findById(ratingId).orElseThrow();
    }

    public Rating getByUserId(String driverId) {
        return ratingRepository.findByUserId(driverId).orElseThrow();
    }
}
