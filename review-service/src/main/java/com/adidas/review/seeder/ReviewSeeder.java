package com.adidas.review.seeder;

import com.adidas.review.entity.Review;
import com.adidas.review.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
public class ReviewSeeder {

    @Autowired
    private ReviewRepository reviewRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedReviewTable();
    }

    public void seedReviewTable() {
        String[] sampleProducts = {"FI6240", "H58190", "H04747", "GM4584", "FJ5135", "GL8608", "EE1152", "GM4509", "FY6872", "HG6508", "BB5476", "S23863", "S82137"};
        Random randValue = new Random();

        Arrays.stream(sampleProducts).forEach(product->{
            Review review = new Review();
            review.setProductId(product);
            BigDecimal bd = new BigDecimal(5 * randValue.nextDouble()).setScale(2, RoundingMode.HALF_UP);
            review.setAverageReviewScore(bd.doubleValue());
            review.setNumberOfReviews(randValue.nextInt(1000));
            log.info(review.toString());
            reviewRepository.save(review);
        });
    }

}
