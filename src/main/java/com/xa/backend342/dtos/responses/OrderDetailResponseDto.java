package com.xa.backend342.dtos.responses;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderDetailResponseDto {
    private Long id;
    private Long headerId;
    private Long variantId;
    private BigDecimal quantity;
    private BigDecimal price;
    private String createdBy;
    private String modifiedBy;
    private boolean isDeleted;
}
