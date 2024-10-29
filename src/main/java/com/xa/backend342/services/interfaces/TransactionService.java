package com.xa.backend342.services.interfaces;

import com.xa.backend342.dtos.requests.TransactionRequestDto;
import com.xa.backend342.dtos.responses.TransactionResponseDto;

public interface TransactionService {
    TransactionResponseDto processTransaction(TransactionRequestDto transactionRequestDto);
}
