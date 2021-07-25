package com.adidas.review.repository;

import com.adidas.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
}
