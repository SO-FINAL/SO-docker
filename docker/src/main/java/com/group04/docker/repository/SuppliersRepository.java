package com.group04.docker.repository;

import com.group04.docker.model.Suppliers;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SuppliersRepository extends MongoRepository<Suppliers, String> {

    boolean existsBy_id(String _id);

}