package com.group04.docker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "chats")
public class Chat {
    private String _id;
    @DocumentReference
    private Customers customer;
    @DocumentReference
    private Suppliers supplier;
    private String sentBy;
    private LocalDateTime sentAt;
    private String message;
}
