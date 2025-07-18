package ua.hudyma.ratingservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.ratingservice.domain.Rating;
import ua.hudyma.ratingservice.service.RatingService;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping("/role")
    public String roleProtectedEndpoint() {
        return "Hello from role protected endpoint!";
    }

    @PostMapping("/add")
    public void addRating (@RequestBody Rating rating){
        ratingService.addRating(rating);
    }

    @PreAuthorize("hasRole('SERVICE')")
    @GetMapping("/{ratingId}")
    private Rating getRating (@PathVariable Long ratingId){
        return ratingService.getById (ratingId);
    }
}
