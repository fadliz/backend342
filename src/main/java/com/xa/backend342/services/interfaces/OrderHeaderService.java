package com.xa.backend342.services.interfaces;

import java.util.List;

import com.xa.backend342.dtos.requests.OrderHeaderRequestDto;
import com.xa.backend342.dtos.responses.OrderHeaderResponseDto;

public interface OrderHeaderService {
    OrderHeaderResponseDto createOrderHeader(OrderHeaderRequestDto orderHeaderRequestDto);

    OrderHeaderResponseDto updateOrderHeader(Long id, OrderHeaderRequestDto orderHeaderRequestDto);

    void deleteOrderHeader(Long id);

    OrderHeaderResponseDto getOrderHeaderById(Long id);

    List<OrderHeaderResponseDto> getOrderHeaders();
}
