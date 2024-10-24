package com.xa.backend342.services.interfaces;

import java.util.List;

import com.xa.backend342.dtos.requests.CategoryRequestDto;
import com.xa.backend342.dtos.responses.CategoryResponseDto;

public interface CategoryService {
    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto);

    void deleteCategory(Long id);

    CategoryResponseDto getCategoryById(Long id);

    List<CategoryResponseDto> getCategories();
}