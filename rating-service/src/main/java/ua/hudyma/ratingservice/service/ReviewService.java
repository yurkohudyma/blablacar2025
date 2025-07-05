package ua.hudyma.ratingservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.hudyma.ratingservice.domain.Review;
import ua.hudyma.ratingservice.dto.ReviewDto;
import ua.hudyma.ratingservice.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewService {

    private final ReviewRepository reviewRepository;
    public void addReview(Review review) {
        reviewRepository.save(review);
    }

    public List<ReviewDto> findAllByRatingIdAndTripId(Long ratingId, String tripId) {
        List<Review> reviews = reviewRepository.findAllByRatingIdAndTripId(ratingId, tripId);
        return convertToDto(reviews);
    }

    private List<ReviewDto> convertToDto(List<Review> reviewList) {
        return reviewList.stream()
                .map(review -> new ReviewDto(
                        review.getGrade(),
                        review.getPublishedOn(),
                        review.getReviewText()))
                .toList();
    }
}
