package com.group04.docker.model.OrderValueObject;

import com.group04.docker.model.Products;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
public class OrderDetails {
    @DocumentReference
    private Products product;
    private int quantity;
    private double discount;
}
