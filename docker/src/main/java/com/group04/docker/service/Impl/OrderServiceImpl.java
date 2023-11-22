package com.group04.docker.service.Impl;

import com.group04.docker.model.Order;
import com.group04.docker.model.OrderValueObject.OrderDetails;
import com.group04.docker.model.Products;
import com.group04.docker.repository.CustomersRepository;
import com.group04.docker.repository.OrderRepository;
import com.group04.docker.repository.ProductsRepository;
import com.group04.docker.service.OrderService;
import com.group04.docker.utils.exception.ResourceNotFoundException;
import com.group04.docker.utils.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomersRepository customerRepository;

    @Autowired
    private ProductsRepository productRepository;

    @Override
    public Order getOrderById(String id) {
        existOrderById(id);
        return orderRepository.findById(id).get();
    }

    @Override
    public Order createOrder(String customerId, Order order) {
        existCustomerById(customerId);
        order.getOrderDetails().forEach(this::validateOrderDetails);
        order.getOrderDetails().forEach(orderDetails -> {
            var product = productRepository.findById(orderDetails.getProduct().get_id()).get();
            isQuantityAvailable(product, orderDetails.getQuantity());
            product.setStock(product.getStock() - orderDetails.getQuantity());
            productRepository.save(product);
            orderDetails.setProduct(product);
        });
        order.setCustomer(customerRepository.findById(customerId).get());
        order.setOrderDate(LocalDateTime.now());
        order.setRequiredDate(LocalDateTime.now().plusDays(14));
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(String id, Order order) {
        existOrderById(id);
        order.getOrderDetails().forEach(this::validateOrderDetails);
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(String id) {
        existOrderById(id);
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    private void existOrderById(String id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("Order with id %s does not exist", id));
        }
    }

    private void existCustomerById(String customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException(String.format("Customer with id %s does not exist", customerId));
        }
    }

    private void validateOrderDetails(OrderDetails orderDetails) {
        if (orderDetails.getProduct().get_id() == null || orderDetails.getProduct().get_id().isEmpty()) {
            throw new ValidationException("Product id is required");
        }
        if (orderDetails.getQuantity() <= 0) {
            throw new ValidationException("Quantity must be greater than 0");
        }
        if (orderDetails.getDiscount() < 0 || orderDetails.getDiscount() > 100) {
            throw new ValidationException("Discount must be between 0 and 100");
        }
    }

    private void isQuantityAvailable(Products product, int quantity) {
        if (product.getStock() < quantity) {
            throw new ValidationException(String.format("Quantity of product %s is not available", product.getProductName()));
        }
    }

}
