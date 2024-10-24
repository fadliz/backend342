package com.xa.backend342.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.xa.backend342.entities.Product;

import lombok.Data;

@Data
public class VariantResponseDto {
    private String slug;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal stock;
    private String createdBy;
    private String modifiedBy;
    private boolean isDeleted;
    private Long productId;
    private Product product;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;
}
