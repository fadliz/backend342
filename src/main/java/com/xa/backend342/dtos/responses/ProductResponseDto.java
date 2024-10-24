package com.xa.backend342.dtos.responses;

import java.time.LocalDateTime;

import com.xa.backend342.entities.Category;

import lombok.Data;

@Data
public class ProductResponseDto {
    private Category category;
    private Long categoryId;
    private String slug;
    private String name;
    private boolean isDeleted;
    private LocalDateTime deletedAt;
    private String createdBy;
    private LocalDateTime createdAt;
    private String modifiedBy;
    private LocalDateTime modifiedAt;
}
