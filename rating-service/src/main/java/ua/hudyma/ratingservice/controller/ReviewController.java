package ua.hudyma.ratingservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.ratingservice.domain.Review;
import ua.hudyma.ratingservice.dto.ReviewDto;
import ua.hudyma.ratingservice.service.RatingService;
import ua.hudyma.ratingservice.service.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final RatingService ratingService;

    @PostMapping("/add/{ratingId}")
    public void addReview (@RequestBody Review review, @PathVariable Long ratingId){
        var rating = ratingService.getReviewById(ratingId);
        if (rating.isPresent()) {
            review.setRating(rating.get());
            reviewService.addReview(review);
        }
        else {
            log.error("no rating {} has been found", ratingId);
        }
    }

    @GetMapping("/{driverId}/{tripId}")
    public List<ReviewDto> getAllReviewsForDriverAndTrip (@PathVariable String driverId,
                                                          @PathVariable String tripId){
        var rating = ratingService.getByUserId(driverId);
        return reviewService.findAllByRatingIdAndTripId(rating.getId(), tripId);
    }
}
