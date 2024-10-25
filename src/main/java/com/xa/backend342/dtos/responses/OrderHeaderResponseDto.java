package com.xa.backend342.dtos.responses;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderHeaderResponseDto {
    private BigDecimal amount;
    private String createdBy;
    private String modifiedBy;
    private boolean isDeleted;
}