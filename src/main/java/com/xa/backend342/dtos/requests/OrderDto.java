package com.xa.backend342.dtos.requests;

import java.util.List;

import lombok.Data;

@Data
public class OrderDto {
    private List<OrderDetailRequestDto> orderDetails;
}