package ua.hudyma.ratingservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.hudyma.ratingservice.domain.Review;
import ua.hudyma.ratingservice.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewService {

    private final ReviewRepository reviewRepository;
    public void addReview(Review review) {
        reviewRepository.save(review);
    }
}
