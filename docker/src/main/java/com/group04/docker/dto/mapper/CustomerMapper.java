package com.group04.docker.dto.mapper;

import com.group04.docker.dto.CustomerRequest;
import com.group04.docker.model.Customers;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customers customerRequestToCustomers(CustomerRequest customerRequest);

}
