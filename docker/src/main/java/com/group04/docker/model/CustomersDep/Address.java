package com.group04.docker.model.CustomersDep;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String country;
    private String city;
    private String street;

    public Address(String country, String city, String street) {
        this.country = country;
        this.city = city;
        this.street = street;
    }
}
