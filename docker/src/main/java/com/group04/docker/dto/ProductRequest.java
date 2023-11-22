package com.group04.docker.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductRequest {
    private String productName;
    private int stock;
    private double unitPrice;
    private List<String> categories;
    private boolean discontinued;
    private String imageUrl;
}
