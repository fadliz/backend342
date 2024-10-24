package com.xa.backend342.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xa.backend342.dtos.requests.CategoryRequestDto;
import com.xa.backend342.dtos.responses.CategoryResponseDto;
import com.xa.backend342.payloads.ApiResponse;
import com.xa.backend342.services.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("*")
public class CategoryRestController {

    @Autowired
    CategoryServiceImpl categoryService;

    @PostMapping("")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.createCategory(categoryRequestDto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), categoryResponseDto));
    }

    @GetMapping("")
    public ResponseEntity<?> getCategories() {
        List<CategoryResponseDto> categoryResponseDtos = categoryService.getCategories();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), categoryResponseDtos));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        CategoryResponseDto categoryResponseDto = categoryService.getCategoryById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), categoryResponseDto));
    }

    @GetMapping("/slug={slug}")
    public ResponseEntity<?> getCategoryById(@PathVariable String slug) {
        CategoryResponseDto categoryResponseDto = categoryService.getCategoryBySlug(slug);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), categoryResponseDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id,
            @RequestBody CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.updateCategory(id, categoryRequestDto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), categoryResponseDto));
    }

    @PutMapping("/slug={slug}")
    public ResponseEntity<?> updateCategoryBySlug(@PathVariable String slug,
            @RequestBody CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.updateCategoryBySlug(slug, categoryRequestDto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), categoryResponseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/slug={slug}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String slug) {
        categoryService.deleteCategory(categoryService.getCategoryBySlug(slug).getId());
        return ResponseEntity.noContent().build();
    }

}
