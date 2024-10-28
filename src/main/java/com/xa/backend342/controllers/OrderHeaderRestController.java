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

import com.xa.backend342.dtos.requests.OrderHeaderRequestDto;
import com.xa.backend342.dtos.responses.OrderHeaderResponseDto;
import com.xa.backend342.payloads.ApiResponse;
import com.xa.backend342.services.impl.OrderHeaderServiceImpl;


@RestController
@RequestMapping("/api/order-header")
@CrossOrigin("http://localhost:9002")
public class OrderHeaderRestController {
    @Autowired
    OrderHeaderServiceImpl orderHeaderService;

    @PostMapping("")
    public ResponseEntity<?> createOrderHeader(@RequestBody OrderHeaderRequestDto orderHeaderRequestDto) {
        OrderHeaderResponseDto orderHeaderResponseDto = orderHeaderService.createOrderHeader(orderHeaderRequestDto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), orderHeaderResponseDto));
    }

    @GetMapping("")
    public ResponseEntity<?> getOrderHeaders() {
        List<OrderHeaderResponseDto> orderHeaderResponseDtos = orderHeaderService.getOrderHeaders();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), orderHeaderResponseDtos));

    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderHeaderById(@PathVariable Long id) {
        OrderHeaderResponseDto orderHeaderResponseDto = orderHeaderService.getOrderHeaderById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), orderHeaderResponseDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderHeader(@PathVariable Long id,
            @RequestBody OrderHeaderRequestDto orderHeaderRequestDto) {
        OrderHeaderResponseDto orderHeaderResponseDto = orderHeaderService.updateOrderHeader(id, orderHeaderRequestDto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), orderHeaderResponseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderHeader(@PathVariable Long id) {
        orderHeaderService.deleteOrderHeader(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ref")
    public ResponseEntity<?> generateReference() {
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), orderHeaderService.createNewReference()));
    }
    
}
