package com.xa.backend342.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xa.backend342.dtos.requests.TransactionRequestDto;
import com.xa.backend342.dtos.responses.TransactionResponseDto;
import com.xa.backend342.payloads.ApiResponse;
import com.xa.backend342.services.impl.TransactionServiceImpl;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin("http://localhost:9002")
public class TransactionRestController {
    @Autowired
    TransactionServiceImpl transactionService;

    @PostMapping("")
    public ResponseEntity<?> processTransaction(@RequestBody TransactionRequestDto transactionRequestDto) {
        TransactionResponseDto transactionResponseDto = transactionService.processTransaction(transactionRequestDto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), transactionResponseDto));
    }
}
