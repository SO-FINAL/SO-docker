package com.group04.docker.service;

import com.group04.docker.model.Customers;

import java.util.List;

public interface CustomerService {
    public abstract Customers createCustomer(Customers customer);
    public abstract Customers getCustomerById(String id);
    public abstract Customers updateCustomer(String id, Customers customer);
    Customers logIn(String email, String password);
    public abstract void deleteCustomer(String id);

    List<Customers> getAllCustomers();
}
