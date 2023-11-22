package com.group04.docker.dto;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderRequest {


    private List<OrderDetailsRequest> orderDetailsRequest;

}
