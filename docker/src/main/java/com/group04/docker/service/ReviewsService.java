package com.group04.docker.service;

import com.group04.docker.model.Reviews;

import java.util.List;

public interface ReviewsService {
    Reviews createReview(Reviews review, String customerId, String productId);
    Reviews getReviewById(String id);
    Reviews updateReview(String id, Reviews review);
    void deleteReview(String id);
    List<Reviews> getAllReviews();
    List<Reviews> getReviewsByProductId(String productId);
}