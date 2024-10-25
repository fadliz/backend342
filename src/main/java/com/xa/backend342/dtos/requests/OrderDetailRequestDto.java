package com.xa.backend342.dtos.requests;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderDetailRequestDto {
    private Long headerId;
    private Long productId;
    private BigDecimal quantity;
    private BigDecimal price;
    private String createdBy;
    private String modifiedBy;
    private boolean isDeleted;
}
