package com.group04.docker.dto.mapper;

import com.group04.docker.dto.ProductRequest;
import com.group04.docker.model.Products;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Products productRequestToProduct(ProductRequest productRequest);

}
