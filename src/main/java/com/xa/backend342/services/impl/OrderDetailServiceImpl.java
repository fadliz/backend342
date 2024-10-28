package com.xa.backend342.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xa.backend342.dtos.requests.OrderDetailRequestDto;
import com.xa.backend342.dtos.requests.OrderDto;
import com.xa.backend342.dtos.responses.OrderDetailResponseDto;
import com.xa.backend342.entities.OrderDetail;
import com.xa.backend342.repositories.OrderDetailRepository;
import com.xa.backend342.services.interfaces.OrderDetailService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderDetailResponseDto createOrderDetail(OrderDetailRequestDto orderDetailRequestDto) {
        // 
        OrderDetail orderDetail = modelMapper.map(orderDetailRequestDto, OrderDetail.class);
        return modelMapper.map(orderDetailRepository.save(orderDetail), OrderDetailResponseDto.class);
    }

    @Override
    public OrderDetailResponseDto getOrderDetailById(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Detail not found"));
        OrderDetailResponseDto orderDetailResponseDto = modelMapper.map(orderDetail, OrderDetailResponseDto.class);
        return orderDetailResponseDto;
    }

    @Override
    public List<OrderDetailResponseDto> getOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        List<OrderDetailResponseDto> orderDetailResponseDtos = orderDetails.stream()
                .map(orderDetail -> modelMapper.map(orderDetail, OrderDetailResponseDto.class))
                .collect(Collectors.toList());
        return orderDetailResponseDtos;
    }

    @Override
    public OrderDetailResponseDto updateOrderDetail(Long id, OrderDetailRequestDto orderDetailRequestDto) {
        // JPA finds the entity by its ID
        // returns Optional util class
        // because entity by ID might not exists
        // Optional is a class with <T> as attribute
        // <T> can be empty/null, isPresent checks the <T> for it
        Optional<OrderDetail> existingOrderDetailOpt = orderDetailRepository.findById(id);
        if (existingOrderDetailOpt.isPresent()) {
            OrderDetail existingOrderDetail = existingOrderDetailOpt.get();
            // 
            if (orderDetailRequestDto.getCreatedBy() == null) {
                orderDetailRequestDto.setCreatedBy(existingOrderDetail.getCreatedBy());
            }
            modelMapper.map(orderDetailRequestDto, existingOrderDetail);
            return modelMapper.map(orderDetailRepository.save(existingOrderDetail), OrderDetailResponseDto.class);
        } else {
            throw new RuntimeException("Order Detail not found");
        }
    }

    @Override
    public void deleteOrderDetail(Long id) {
        orderDetailRepository.deleteById(id);
    }

    public List<OrderDetailResponseDto> createOrderDetails(OrderDto orderRequestDto) {
        List<OrderDetailResponseDto>  orderDetailResponseDtos = new ArrayList<>();
        for (OrderDetailRequestDto orderDetailRequestDto : orderRequestDto.getOrderDetails()) {            
            OrderDetail orderDetail = modelMapper.map(orderDetailRequestDto, OrderDetail.class);
            OrderDetailResponseDto orderDetailResponseDto = modelMapper.map(orderDetailRepository.save(orderDetail), OrderDetailResponseDto.class);
            orderDetailResponseDtos.add(orderDetailResponseDto);
        }
        return orderDetailResponseDtos;
    }

}
