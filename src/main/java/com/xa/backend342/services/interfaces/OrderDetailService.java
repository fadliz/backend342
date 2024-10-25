package com.xa.backend342.services.interfaces;

import java.util.List;

import com.xa.backend342.dtos.requests.OrderDetailRequestDto;
import com.xa.backend342.dtos.responses.OrderDetailResponseDto;

public interface OrderDetailService {
    OrderDetailResponseDto createOrderDetail(OrderDetailRequestDto orderDetailRequestDto);

    OrderDetailResponseDto updateOrderDetail(Long id, OrderDetailRequestDto orderDetailRequestDto);

    void deleteOrderDetail(Long id);

    OrderDetailResponseDto getOrderDetailById(Long id);

    List<OrderDetailResponseDto> getOrderDetails();
}
