package com.group04.docker.dto.mapper;

import com.group04.docker.dto.OrderDetailsRequest;
import com.group04.docker.dto.OrderRequest;
import com.group04.docker.model.Order;
import com.group04.docker.model.OrderValueObject.OrderDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "orderDetailsRequest", target = "orderDetails", qualifiedByName = "orderDetailsRequestToOrderDetails")
    Order orderRequestToOrder(OrderRequest orderRequest);

    @Named("orderDetailsRequestToOrderDetails")
    public static OrderDetails orderDetailsRequestToOrderDetails(OrderDetailsRequest orderDetailsRequest){
        return OrderDetailsMapper.INSTANCE.orderDetailsRequestToOrderDetails(orderDetailsRequest);
    }
}
