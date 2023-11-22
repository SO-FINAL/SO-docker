package com.group04.docker.controller;

import com.group04.docker.dto.ReviewRequest;
import com.group04.docker.dto.mapper.ReviewMapper;
import com.group04.docker.model.Reviews;
import com.group04.docker.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ReviewsController {
    @Autowired
    private ReviewsService reviewService;

    @PostMapping("/product/{productId}/customer/{customerId}/reviews")
    public Reviews createReview(@RequestBody ReviewRequest reviewRequest, @PathVariable String customerId, @PathVariable String productId) {
        var review = ReviewMapper.INSTANCE.reviewRequestToReview(reviewRequest);
        return reviewService.createReview(review, customerId, productId);
    }

    @GetMapping("/reviews/{id}")
    public Reviews getReviewById(@PathVariable String id) {
        return reviewService.getReviewById(id);
    }

    @PutMapping("/reviews/{id}")
    public Reviews updateReview(@PathVariable String id, @RequestBody ReviewRequest reviewRequest) {
        var review = ReviewMapper.INSTANCE.reviewRequestToReview(reviewRequest);
        return reviewService.updateReview(id, review);
    }

    @DeleteMapping("/reviews/{id}")
    public void deleteReview(@PathVariable String id) {
        reviewService.deleteReview(id);
    }

    @GetMapping("/reviews")
    public List<Reviews> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/reviews/product/{productId}")
    public List<Reviews> getReviewsByProductId(@PathVariable String productId) {
        return reviewService.getReviewsByProductId(productId);
    }
}