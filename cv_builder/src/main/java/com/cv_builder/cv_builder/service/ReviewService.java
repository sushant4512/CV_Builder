package com.cv_builder.cv_builder.service;

import com.cv_builder.cv_builder.entity.Review;
import com.cv_builder.cv_builder.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, Review reviewDetails) {
        Review review = reviewRepository.findById(id).orElseThrow();
        review.setName(reviewDetails.getName());
        review.setContent(reviewDetails.getContent());
        review.setRating(reviewDetails.getRating());
        review.setDate(reviewDetails.getDate());
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
