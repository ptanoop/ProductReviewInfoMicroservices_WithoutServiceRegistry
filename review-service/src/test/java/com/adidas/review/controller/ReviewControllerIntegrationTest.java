package com.adidas.review.controller;

import com.adidas.review.entity.Review;
import com.adidas.review.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)
@Slf4j
class ReviewControllerIntegrationTest {

    @Autowired
    private ReviewController reviewController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    void getReviewById_successCase() throws Exception {
        Review review = new Review("FI6240", 4.3, 10);
        when(reviewService.getReviewById("FI6240")).thenReturn(java.util.Optional.of(review));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reviews/FI6240")
                .with(user("admin").password("admin").roles("USER","ADMIN"));
        mockMvc.perform(requestBuilder).andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value("FI6240"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.averageReviewScore").value(4.3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfReviews").value(10))
                .andExpect(status().isOk());
    }

    @Test
    void getReviewById_NotFoundCase() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reviews/100")
                .with(user("admin").password("admin").roles("USER","ADMIN"));
        mockMvc.perform(requestBuilder).andDo(print())
                .andExpect(MockMvcResultMatchers.content().string(""))
                .andExpect(status().isNotFound());
    }

    @Test
    void save_success() throws Exception {
        Review review = new Review("ABCDEF", 4.5, 100);
        when(reviewService.save(any(Review.class))).thenReturn(review);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/reviews/save")
                .with(user("admin").password("admin").roles("USER","ADMIN"));
        mockMvc.perform(((MockHttpServletRequestBuilder) requestBuilder).content(new ObjectMapper().writeValueAsString(review))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value("ABCDEF"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.averageReviewScore").value(4.5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfReviews").value(100));
    }

    @Test
    void save_failure() throws Exception {
        Review review = new Review("ABCD",0.0,0);
        when(reviewService.save(any(Review.class))).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/reviews/save")
                .with(user("admin").password("admin").roles("USER","ADMIN"));
        mockMvc.perform(((MockHttpServletRequestBuilder) requestBuilder).content(new ObjectMapper().writeValueAsString(review))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andDo(print());
    }

    @Test
    void update_failure() throws Exception {
        Review review = new Review("ABCDEF", 4.5, 100);
        when(reviewService.save(any(Review.class))).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/reviews/update")
                .with(user("admin").password("admin").roles("USER","ADMIN"));
        mockMvc.perform(((MockHttpServletRequestBuilder) requestBuilder).content(new ObjectMapper().writeValueAsString(review))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }



}
