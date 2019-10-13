package me.grace.dongnestaurant.application;

import me.grace.dongnestaurant.domain.Review;
import me.grace.dongnestaurant.domain.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class ReviewServiceTests {

    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    public void add() {
        Review review = Review.builder()
                .name("grace")
                .description("맛있다")
                .build();
        reviewService.addReview(1L, review);

        verify(reviewRepository).save(any());
    }

}