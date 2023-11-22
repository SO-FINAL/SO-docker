package com.group04.docker.repository;

import com.group04.docker.model.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductsRepository extends MongoRepository<Products, String> {
    boolean existsBy_id(String _id);
}