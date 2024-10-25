package com.xa.backend342.dtos.requests;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderHeaderRequestDto {
    private BigDecimal amount;
    private String createdBy;
    private String modifiedBy;
    private boolean isDeleted;
}
