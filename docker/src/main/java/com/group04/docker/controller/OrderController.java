package com.group04.docker.controller;

import com.group04.docker.dto.OrderRequest;
import com.group04.docker.dto.mapper.OrderMapper;
import com.group04.docker.model.Order;
import com.group04.docker.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Transactional(readOnly = true)
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/customers/{customerId}/orders")
    public ResponseEntity<Order> createOrder(@PathVariable String customerId, @RequestBody OrderRequest orderRequest) {
        var order = OrderMapper.INSTANCE.orderRequestToOrder(orderRequest);
        return new ResponseEntity<>(orderService.createOrder(customerId, order), HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable String id, @RequestBody OrderRequest orderRequest) {
        var order = OrderMapper.INSTANCE.orderRequestToOrder(orderRequest);
        return new ResponseEntity<>(orderService.updateOrder(id, order), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(String.format("Order with id %s was successfully deleted", id), HttpStatus.NO_CONTENT);
    }

}
