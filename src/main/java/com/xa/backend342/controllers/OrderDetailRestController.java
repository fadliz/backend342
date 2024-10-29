package com.xa.backend342.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xa.backend342.dtos.requests.OrderDetailRequestDto;
import com.xa.backend342.dtos.responses.OrderDetailResponseDto;
import com.xa.backend342.payloads.ApiResponse;
import com.xa.backend342.services.impl.OrderDetailServiceImpl;

@RestController
@RequestMapping("/api/order-detail")
@CrossOrigin("http://localhost:9002")
public class OrderDetailRestController {
    @Autowired
    OrderDetailServiceImpl orderDetailService;

    @PostMapping("")
    public ResponseEntity<?> createOrderDetail(@RequestBody OrderDetailRequestDto orderDetailRequestDto) {
        OrderDetailResponseDto orderDetailResponseDto = orderDetailService.createOrderDetail(orderDetailRequestDto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), orderDetailResponseDto));
    }

    @GetMapping("")
    public ResponseEntity<?> getOrderDetails() {
        List<OrderDetailResponseDto> orderDetailResponseDtos = orderDetailService.getOrderDetails();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), orderDetailResponseDtos));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetailById(@PathVariable Long id) {
        OrderDetailResponseDto orderDetailResponseDto = orderDetailService.getOrderDetailById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), orderDetailResponseDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetail(@PathVariable Long id,
            @RequestBody OrderDetailRequestDto orderDetailRequestDto) {
        OrderDetailResponseDto orderDetailResponseDto = orderDetailService.updateOrderDetail(id, orderDetailRequestDto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), orderDetailResponseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long id) {
        orderDetailService.deleteOrderDetail(id);
        return ResponseEntity.noContent().build();
    }
    
}
