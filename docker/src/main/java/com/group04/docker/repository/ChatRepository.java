package com.group04.docker.repository;

import com.group04.docker.model.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Chat, String> {
    boolean existsBy_id(String _id);
}
