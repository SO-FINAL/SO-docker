package com.group04.docker.dto.mapper;

import com.group04.docker.dto.OrderDetailsRequest;
import com.group04.docker.model.OrderValueObject.OrderDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderDetailsMapper {

    OrderDetailsMapper INSTANCE = Mappers.getMapper(OrderDetailsMapper.class);

    @Mapping(source = "orderDetailsRequest.productId", target = "product._id")
    OrderDetails orderDetailsRequestToOrderDetails(OrderDetailsRequest orderDetailsRequest);

}
