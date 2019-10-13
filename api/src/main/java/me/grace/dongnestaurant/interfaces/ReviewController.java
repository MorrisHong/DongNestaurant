package me.grace.dongnestaurant.interfaces;

import me.grace.dongnestaurant.application.ReviewService;
import me.grace.dongnestaurant.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> create(@Valid @RequestBody Review resource,
                                    @PathVariable("restaurantId") Long restaurantId) throws URISyntaxException {

        Review review = reviewService.addReview(restaurantId, resource);
        String str = "/restaurants/" + restaurantId + "/reviews/" + review.getId();
        URI url = new URI(str);
        return ResponseEntity.created(url).body("{}");
    }
}
