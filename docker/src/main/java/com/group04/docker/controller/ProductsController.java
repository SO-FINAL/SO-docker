package com.group04.docker.controller;

import com.group04.docker.dto.ProductRequest;
import com.group04.docker.dto.mapper.ProductMapper;
import com.group04.docker.model.Products;
import com.group04.docker.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @PostMapping("/suppliers/{id}/products")
    public Products createProduct(@PathVariable String id ,@RequestBody ProductRequest productRequest) {
        var product = ProductMapper.INSTANCE.productRequestToProduct(productRequest);
        return productsService.createProduct(id, product);
    }

    @GetMapping("/products/{id}")
    public Products getProductById(@PathVariable String id) {
        return productsService.getProductById(id);
    }

    @PutMapping("/products/{id}")
    public Products updateProduct(@PathVariable String id, @RequestBody ProductRequest productRequest) {
        var product = ProductMapper.INSTANCE.productRequestToProduct(productRequest);
        return productsService.updateProduct(id, product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable String id) {
        productsService.deleteProduct(id);
    }

    @GetMapping("/products")
    public List<Products> getAllProducts() {
        return productsService.getAllProducts();
    }
}