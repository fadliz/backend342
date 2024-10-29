package com.xa.backend342.dtos.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponseDto {
    private OrderHeaderResponseDto orderHeaderResponseDto;
    private List<OrderDetailResponseDto> orderDetailResponseDto;
}
