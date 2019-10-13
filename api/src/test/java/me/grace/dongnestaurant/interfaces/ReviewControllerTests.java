package me.grace.dongnestaurant.interfaces;

import me.grace.dongnestaurant.application.ReviewService;
import me.grace.dongnestaurant.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;


    @Test
    public void create_with_vaild() throws Exception {
        given(reviewService.addReview(eq(1L), any())).willReturn(
                Review.builder()
                .id(123L)
                        .name("grace")
                        .score(3)
                        .description("맛있습니다.")
                        .build()
        );

        mockMvc.perform(post("/restaurants/1/reviews")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content("{\"name\":\"grace\",\"score\":3,\"description\":\"맛있습니다.\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/restaurants/1/reviews/123"));

        verify(reviewService).addReview(eq(1L),any());
    }

    @Test
    public void create_with_invaild() throws Exception {
        mockMvc.perform(post("/restaurants/1/reviews")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"name\":\"\",\"score\":3,\"description\":\"\"}"))
                .andExpect(status().isBadRequest());

        verify(reviewService, never()).addReview(eq(1L),any());
    }


}