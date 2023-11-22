package com.group04.docker.service.Impl;

import com.group04.docker.model.Products;
import com.group04.docker.repository.ProductsRepository;
import com.group04.docker.repository.SuppliersRepository;
import com.group04.docker.service.ProductsService;
import com.group04.docker.utils.exception.ResourceNotFoundException;
import com.group04.docker.utils.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private SuppliersRepository suppliersRepository;

    @Override
    public Products createProduct(String supplierId, Products product) {
        existsSupplierById(supplierId);
        validateProduct(product);
        product.setSupplier(suppliersRepository.findById(supplierId).get());
        return productsRepository.save(product);
    }

    @Override
    public Products getProductById(String id) {
        existsById(id);
        return productsRepository.findById(id).orElse(null);
    }

    @Override
    public Products updateProduct(String id, Products product) {
        existsById(id);
        validateProduct(product);
        return productsRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setProductName(product.getProductName());
                    existingProduct.setStock(product.getStock());
                    existingProduct.setUnitPrice(product.getUnitPrice());
                    existingProduct.setCategories(product.getCategories());
                    existingProduct.setDiscontinued(product.isDiscontinued());
                    existingProduct.setImageUrl(product.getImageUrl());
                    existingProduct.setSupplier(product.getSupplier()); // handle new field
                    return productsRepository.save(existingProduct);
                })
                .orElse(null);
    }

    @Override
    public void deleteProduct(String id) {
        existsById(id);
        productsRepository.deleteById(id);
    }

    @Override
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    private void validateProduct(Products product) {
        if (product.getProductName() == null || product.getProductName().isEmpty())
            throw new ValidationException("Product name is required");
        if (product.getStock() < 0)
            throw new ValidationException("Stock must be greater than 0");
        if (product.getUnitPrice() <= 0)
            throw new ValidationException("Unit price must be greater than 0");
        if (product.getCategories() == null || product.getCategories().isEmpty())
            throw new ValidationException("Categories is required");
        if (product.getImageUrl() == null || product.getImageUrl().isEmpty())
            throw new ValidationException("Image url is required");
    }

    private void existsById(String id) {
        if (!productsRepository.existsBy_id(id))
            throw new ResourceNotFoundException("Product with id " + id + " does not exist");
    }

    private void existsSupplierById(String supplierId) {
        if (!suppliersRepository.existsBy_id(supplierId))
            throw new ResourceNotFoundException("Supplier with id " + supplierId + " does not exist");
    }
}