package com.xa.backend342.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xa.backend342.dtos.requests.TransactionRequestDto;
import com.xa.backend342.dtos.responses.OrderDetailResponseDto;
import com.xa.backend342.dtos.responses.OrderHeaderResponseDto;
import com.xa.backend342.dtos.responses.TransactionResponseDto;
import com.xa.backend342.services.interfaces.TransactionService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private OrderHeaderServiceImpl orderHeaderService;
    @Autowired
    private OrderDetailServiceImpl orderDetailService;

    @Override
    public TransactionResponseDto processTransaction(TransactionRequestDto transactionRequestDto) {
        OrderHeaderResponseDto orderHeaderResponseDto = orderHeaderService
                .createOrderHeader(transactionRequestDto.getOrderHeader());
        List<OrderDetailResponseDto> orderDetailResponseDtos = orderDetailService
                .createOrderDetails(transactionRequestDto.getOrderDetails(), orderHeaderResponseDto.getId());
        return new TransactionResponseDto(orderHeaderResponseDto, orderDetailResponseDtos);
    }

}
