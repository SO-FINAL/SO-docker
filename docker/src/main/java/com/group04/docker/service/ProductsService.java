package com.group04.docker.service;

import com.group04.docker.model.Products;

import java.util.List;

public interface ProductsService {
    Products createProduct(String supplierId, Products product);
    Products getProductById(String id);
    Products updateProduct(String id, Products product);
    void deleteProduct(String id);
    List<Products> getAllProducts();
}