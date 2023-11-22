package com.group04.docker.model;

import com.group04.docker.model.OrderValueObject.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {

    @Id
    private String _id;
    private LocalDateTime orderDate;
    private LocalDateTime requiredDate;
    @DocumentReference
    private Customers customer;
    private List<OrderDetails> orderDetails;
}
