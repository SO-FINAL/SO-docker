package com.group04.docker.dto;

import com.group04.docker.model.CustomersDep.Address;
import lombok.Data;

@Data
public class CustomerRequest {
    private String name;
    private String lastName;
    private String password;
    private String phone;
    private String email;
    private Address address;
}
