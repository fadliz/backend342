package com.xa.backend342.dtos.requests;

import lombok.Data;

@Data
public class CategoryRequestDto {
    // private String id;
    private String name;
    private String slug;
    private String createdBy;
    private String modifiedBy;
    private boolean isDeleted;
}
