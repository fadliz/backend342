package com.xa.backend342.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.xa.backend342.entities.Variant;

import lombok.Data;

@Data
public class VariantResponseDto {
    private Variant variant;
    private Long variantId;
    private String slug;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal stock;
    private boolean isDeleted;
    private LocalDateTime deletedAt;
    private String createdBy;
    private LocalDateTime createdAt;
    private String modifiedBy;
    private LocalDateTime modifiedAt;
}
