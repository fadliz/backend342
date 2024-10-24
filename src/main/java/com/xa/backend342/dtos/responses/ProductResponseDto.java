package com.xa.backend342.dtos.responses;

import java.time.LocalDateTime;

import com.xa.backend342.entities.Category;

import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;
    private String slug;
    private String name;    
    private String createdBy;
    private String modifiedBy;
    private boolean isDeleted;
    private Long categoryId;
    private Category category;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;
}
