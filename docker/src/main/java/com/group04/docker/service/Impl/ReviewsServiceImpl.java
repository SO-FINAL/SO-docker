package com.group04.docker.service.Impl;

import com.group04.docker.model.Reviews;
import com.group04.docker.repository.CustomersRepository;
import com.group04.docker.repository.ProductsRepository;
import com.group04.docker.repository.ReviewsRepository;
import com.group04.docker.service.ReviewsService;
import com.group04.docker.utils.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewsService {
    @Autowired
    private ReviewsRepository reviewRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public Reviews createReview(Reviews review, String customerId, String productId) {
        existsCustomerById(customerId);
        existsProductById(productId);
        validateReview(review);
        review.setCustomer(customersRepository.findById(customerId).get());
        review.setProduct(productsRepository.findById(productId).get());
        return reviewRepository.save(review);
    }

    @Override
    public Reviews getReviewById(String id) {
        existsById(id);
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public Reviews updateReview(String id, Reviews review) {
        existsById(id);
        validateReview(review);
        return reviewRepository.findById(id)
                .map(existingReview -> {
                    existingReview.setMessage(review.getMessage());
                    // update other fields
                    return reviewRepository.save(existingReview);
                })
                .orElse(null);
    }

    @Override
    public void deleteReview(String id) {
        existsById(id);
        reviewRepository.deleteById(id);
    }

    @Override
    public List<Reviews> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Reviews> getReviewsByProductId(String productId) {
        existsProductById(productId);
        return reviewRepository.findByProduct__id(productId);
    }

    private void validateReview(Reviews review) {
        if (review.getMessage() == null || review.getMessage().isEmpty()) {
            throw new ResourceNotFoundException("Review message is required");
        }
    }

    private void existsProductById(String productId) {
        if (!productsRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product with id " + productId + " does not exist");
        }
    }

    private void existsCustomerById(String customerId) {
        if (!customersRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer with id " + customerId + " does not exist");
        }
    }

    private void existsById(String id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("Review with id " + id + " does not exist");
        }
    }

}
