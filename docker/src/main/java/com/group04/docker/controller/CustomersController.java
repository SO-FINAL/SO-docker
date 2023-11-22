package com.group04.docker.controller;

import com.group04.docker.dto.CustomerRequest;
import com.group04.docker.dto.LoginRequest;
import com.group04.docker.dto.mapper.CustomerMapper;
import com.group04.docker.model.Customers;
import com.group04.docker.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin(origins = "*")
public class CustomersController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customers createCustomer(@RequestBody CustomerRequest customerRequest) {
        var customer = CustomerMapper.INSTANCE.customerRequestToCustomers(customerRequest);
        return customerService.createCustomer(customer);
    }

    @GetMapping("/{id}")
    public Customers getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public List<Customers> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PutMapping("/{id}")
    public Customers updateCustomer(@PathVariable String id, @RequestBody CustomerRequest customerRequest) {
        var customer = CustomerMapper.INSTANCE.customerRequestToCustomers(customerRequest);
        return customerService.updateCustomer(id, customer);
    }

    @PostMapping("/login")
    public ResponseEntity<Customers> logIn(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(customerService.logIn(loginRequest.getEmail(), loginRequest.getPassword()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
    }
}