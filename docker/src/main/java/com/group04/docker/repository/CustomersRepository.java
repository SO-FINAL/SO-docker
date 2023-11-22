package com.group04.docker.repository;

import com.group04.docker.model.Customers;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomersRepository extends MongoRepository<Customers, String> {

    boolean existsBy_id(String _id);
    boolean existsByEmailAndPassword(String email, String password);

    Customers findByEmail(String email);

}
