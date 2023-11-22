package com.group04.docker.repository;

import com.group04.docker.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {

    boolean existsBy_id(String id);

}
