package com.xa.backend342.dtos.requests;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class VariantRequestDto {
    private Long productId;
    private String slug;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal stock;
    private String createdBy;
    private String modifiedBy;
    private boolean isDeleted;
}
