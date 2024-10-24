package com.xa.backend342.dtos.responses;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CategoryResponseDto {
    private String name;
    private String slug;
    private String createdBy;
    private String modifiedBy;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;
}
