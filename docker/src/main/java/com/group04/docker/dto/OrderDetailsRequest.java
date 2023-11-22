package com.group04.docker.dto;

import lombok.Data;

@Data
public class OrderDetailsRequest {
    private String productId;
    private int quantity;
    private double discount;
}
