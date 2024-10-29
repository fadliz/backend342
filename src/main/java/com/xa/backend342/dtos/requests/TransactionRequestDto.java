package com.xa.backend342.dtos.requests;

import java.util.List;

import lombok.Data;

@Data
public class TransactionRequestDto {
    private OrderHeaderRequestDto orderHeader;
    private List<OrderDetailRequestDto> orderDetails;
}
