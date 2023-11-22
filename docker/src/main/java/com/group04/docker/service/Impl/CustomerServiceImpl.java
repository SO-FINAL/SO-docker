package com.group04.docker.service.Impl;

import com.group04.docker.model.Customers;
import com.group04.docker.repository.CustomersRepository;
import com.group04.docker.service.CustomerService;
import com.group04.docker.utils.exception.ResourceNotFoundException;
import com.group04.docker.utils.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomersRepository customersRepository;

    @Override
    public Customers createCustomer(Customers customer) {
        validateCustomer(customer);
        return customersRepository.save(customer);
    }


    @Override
    public Customers getCustomerById(String id) {
        existCustomer(id);
        return customersRepository.findById(id).orElse(null);
    }

    @Override
    public Customers updateCustomer(String id, Customers customer) {
        existCustomer(id);
        validateCustomer(customer);
        return customersRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setName(customer.getName());
                    existingCustomer.setLastName(customer.getLastName());
                    existingCustomer.setPassword(customer.getPassword());
                    existingCustomer.setPhone(customer.getPhone());
                    existingCustomer.setEmail(customer.getEmail());
                    existingCustomer.setAddress(customer.getAddress());
                    return customersRepository.save(existingCustomer);
                })
                .orElse(null);
    }

    @Override
    public Customers logIn(String email, String password) {
        existsCustomerByEmailAndPassword(email, password);
        return customersRepository.findByEmail(email);
    }

    private void existsCustomerByEmailAndPassword(String email, String password) {
        if (!customersRepository.existsByEmailAndPassword(email, password))
            throw new ValidationException("Wrong email or password");
    }

    @Override
    public void deleteCustomer(String id) { // changed from int to String
        existCustomer(id);
        customersRepository.deleteById(id);
    }

    @Override
    public List<Customers> getAllCustomers() {
        return customersRepository.findAll();
    }

    private void validateCustomer(Customers customer) {
        if (customer.getName() == null || customer.getName().isEmpty()) {
            throw new ValidationException("Name is required");
        }
        if (customer.getLastName() == null || customer.getLastName().isEmpty()) {
            throw new ValidationException("Last name is required");
        }
        if (customer.getPassword() == null || customer.getPassword().isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (customer.getPhone() == null || customer.getPhone().isEmpty()) {
            throw new ValidationException("Phone is required");
        }
        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            throw new ValidationException("Email is required");
        }
        if (customer.getAddress() == null) {
            throw new ValidationException("Address is required");
        }
        if (customer.getAddress().getStreet() == null || customer.getAddress().getStreet().isEmpty()) {
            throw new ValidationException("Street is required");
        }
        if (customer.getAddress().getCity() == null || customer.getAddress().getCity().isEmpty()) {
            throw new ValidationException("City is required");
        }
        if (customer.getAddress().getCountry() == null || customer.getAddress().getCountry().isEmpty()) {
            throw new ValidationException("Country is required");
        }
    }

    private void existCustomer(String id) {
        if (!customersRepository.existsBy_id(id))
            throw new ResourceNotFoundException("Customer with id " + id + " does not exist");
    }
}