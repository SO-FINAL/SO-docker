package com.group04.docker.service;

import com.group04.docker.model.Order;

import java.util.List;

public interface OrderService {

    Order getOrderById(String id);
    Order createOrder(String customerId, Order order);
    Order updateOrder(String id, Order order);
    void deleteOrder(String id);
    List<Order> getAllOrders();
}
