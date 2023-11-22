package com.group04.docker.model;

import com.group04.docker.model.CustomersDep.Address;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
public class Customers {
    @Id
    private String _id; // changed from int to String
    private String name;
    private String lastName;
    private String password;
    private String phone;
    private String email;

    private Address address;
}