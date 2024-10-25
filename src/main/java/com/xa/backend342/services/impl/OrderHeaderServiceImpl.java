package com.xa.backend342.services.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xa.backend342.dtos.requests.OrderHeaderRequestDto;
import com.xa.backend342.dtos.responses.OrderHeaderResponseDto;
import com.xa.backend342.entities.OrderHeader;
import com.xa.backend342.repositories.OrderHeaderRepository;
import com.xa.backend342.services.interfaces.OrderHeaderService;
import com.xa.backend342.utils.ReferenceUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderHeaderServiceImpl implements OrderHeaderService {

    @Autowired
    private OrderHeaderRepository orderHeaderRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderHeaderResponseDto createOrderHeader(OrderHeaderRequestDto orderHeaderRequestDto) {
        //
        OrderHeader orderHeader = modelMapper.map(orderHeaderRequestDto, OrderHeader.class);
        orderHeader.setReference(this.createNewReference());
        return modelMapper.map(orderHeaderRepository.save(orderHeader), OrderHeaderResponseDto.class);
    }

    @Override
    public OrderHeaderResponseDto getOrderHeaderById(Long id) {
        OrderHeader orderHeader = orderHeaderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Header not found"));
        OrderHeaderResponseDto orderHeaderResponseDto = modelMapper.map(orderHeader, OrderHeaderResponseDto.class);
        return orderHeaderResponseDto;
    }

    @Override
    public List<OrderHeaderResponseDto> getOrderHeaders() {
        List<OrderHeader> orderHeaders = orderHeaderRepository.findAll();
        List<OrderHeaderResponseDto> orderHeaderResponseDtos = orderHeaders.stream()
                .map(orderHeader -> modelMapper.map(orderHeader, OrderHeaderResponseDto.class))
                .collect(Collectors.toList());
        return orderHeaderResponseDtos;
    }

    @Override
    public OrderHeaderResponseDto updateOrderHeader(Long id, OrderHeaderRequestDto orderHeaderRequestDto) {
        // JPA finds the entity by its ID
        // returns Optional util class
        // because entity by ID might not exists
        // Optional is a class with <T> as attribute
        // <T> can be empty/null, isPresent checks the <T> for it
        Optional<OrderHeader> existingOrderHeaderOpt = orderHeaderRepository.findById(id);
        if (existingOrderHeaderOpt.isPresent()) {
            OrderHeader existingOrderHeader = existingOrderHeaderOpt.get();
            //
            if (orderHeaderRequestDto.getCreatedBy() == null) {
                orderHeaderRequestDto.setCreatedBy(existingOrderHeader.getCreatedBy());
            }
            modelMapper.map(orderHeaderRequestDto, existingOrderHeader);
            return modelMapper.map(orderHeaderRepository.save(existingOrderHeader), OrderHeaderResponseDto.class);
        } else {
            throw new RuntimeException("Order Header not found");
        }
    }

    @Override
    public void deleteOrderHeader(Long id) {
        orderHeaderRepository.deleteById(id);
    }

    public String createNewReference() {
        String yearMonth = getCurrentYearMonth();
        Optional<String> lastReference = orderHeaderRepository.findLatestReference("SLS-" + yearMonth + "-%");
        return ReferenceUtil.generateReference(lastReference.orElse(null));
    }

    private String getCurrentYearMonth() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyMM"));
    }

}
