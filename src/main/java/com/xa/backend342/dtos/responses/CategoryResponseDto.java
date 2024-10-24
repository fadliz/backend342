package com.xa.backend342.dtos.responses;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CategoryResponseDto {
    private String name;
    private String slug;
    private boolean isDeleted;
    private LocalDateTime deletedAt;
    private String createdBy;
    private LocalDateTime createdAt;
    private String modifiedBy;
    private LocalDateTime modifiedAt;
}
