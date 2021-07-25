package com.adidas.review.controller;

import com.adidas.review.entity.Review;
import com.adidas.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
@Slf4j
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{productId}")
    public ResponseEntity<Review> getReviewById(@PathVariable String productId){
        Optional<Review> reviewOpt = reviewService.getReviewById(productId);
        if(reviewOpt.isPresent())
            return new ResponseEntity<>(reviewOpt.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<Review> saveReview(@RequestBody Review review){
        Review savedReview = reviewService.save(review);
        if(savedReview!=null)
            return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/update")
    public ResponseEntity<Review> updateReview(@RequestBody Review review){
        if(reviewService.getReviewById(review.getProductId()).isPresent()){
            Review updatedReview = reviewService.save(review);
            if(updatedReview!=null)
                return new ResponseEntity<>(updatedReview, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Boolean> deleteReview(@PathVariable String productId){
        reviewService.deleteReview(productId);
        return new ResponseEntity<>(new Boolean(Boolean.TRUE), HttpStatus.OK);
    }

}
