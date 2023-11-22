package com.group04.docker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.lang.reflect.Array;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class Products {
    @Id
    private String _id;
    private String productName;
    private int stock;
    private double unitPrice;
    private List<String> categories;
    private boolean discontinued;
    private String imageUrl;
    @DocumentReference
    private Suppliers supplier;
}