package com.xa.backend342.dtos.requests;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String slug;
    private String name;    
    private String createdBy;
    private String modifiedBy;
    private boolean isDeleted;
    private Long categoryId;
}
