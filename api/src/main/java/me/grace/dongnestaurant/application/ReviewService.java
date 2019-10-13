package me.grace.dongnestaurant.application;

import lombok.NoArgsConstructor;
import me.grace.dongnestaurant.domain.Review;
import me.grace.dongnestaurant.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ReviewService {

    ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Long restaurantId, Review review) {
        review.setRestaurantId(restaurantId);
        return this.reviewRepository.save(review);
    }
}
