package com.adidas.review.service;

import com.adidas.review.entity.Review;
import com.adidas.review.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Optional<Review> getReviewById(String productId) {
        return reviewRepository.findById(productId);
    }

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public void deleteReview(String productId) {
        reviewRepository.deleteById(productId);
    }
}
