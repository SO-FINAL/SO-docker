package com.group04.docker.dto.mapper;

import com.group04.docker.dto.SupplierRequest;
import com.group04.docker.model.Suppliers;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SupplierMapper {
    SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);
    Suppliers supplierRequestToSupplier(SupplierRequest supplierRequest);
}
