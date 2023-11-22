package com.group04.docker.repository;

import com.group04.docker.model.Reviews;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewsRepository extends MongoRepository<Reviews, String> {
    List<Reviews> findByProduct__id(String productId);
    boolean existsBy_id(String id);
}