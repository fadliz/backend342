package com.xa.backend342.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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
import com.xa.backend342.entities.Category;
import com.xa.backend342.services.impl.CategoryServiceImpl;
import com.xa.backend342.utils.SlugUtils;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("*")
public class CategoryRestController {

    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping("")
    public ResponseEntity<?> getCategories() {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        try {
            List<Category> categories = categoryService.getCategories();
            List<CategoryResponseDto> categoryResponseDtos = categories.stream()
                    .map(category -> modelMapper.map(category, CategoryResponseDto.class))
                    .collect(Collectors.toList());
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", categoryResponseDtos);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "success");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        try {
            Category category = categoryService.getCategory(id);
            CategoryResponseDto categoryResponseDto = modelMapper.map(category, CategoryResponseDto.class);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", categoryResponseDto);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "success");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/slug={slug}")
    public ResponseEntity<?> getCategoryById(@PathVariable String slug) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        try {
            Category category = categoryService.getCategoryBySlug(slug);
            CategoryResponseDto categoryResponseDto = modelMapper.map(category, CategoryResponseDto.class);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", categoryResponseDto);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "success");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id,
            @RequestBody CategoryRequestDto categoryRequestDto) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        if (categoryRequestDto.getSlug() == null) {
            categoryRequestDto.setSlug(SlugUtils.toSlug(categoryRequestDto.getName()));
        }
        try {
            Category category = modelMapper.map(categoryRequestDto, Category.class);
            Category updatedCategory = categoryService.updateCategory(id, category);
            CategoryResponseDto categoryResponseDto = modelMapper.map(updatedCategory, CategoryResponseDto.class);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", categoryResponseDto);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "success");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/slug={slug}")
    public ResponseEntity<?> updateCategoryBySlug(@PathVariable String slug,
            @RequestBody CategoryRequestDto categoryRequestDto) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        if (categoryRequestDto.getSlug() == null) {
            categoryRequestDto.setSlug(SlugUtils.toSlug(categoryRequestDto.getName()));
        }
        try {
            Category category = modelMapper.map(categoryRequestDto, Category.class);
            Category updatedCategory = categoryService.updateCategoryBySlug(slug, category);
            CategoryResponseDto categoryResponseDto = modelMapper.map(updatedCategory, CategoryResponseDto.class);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", categoryResponseDto);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "success");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        if (categoryRequestDto.getSlug() == null) {
            categoryRequestDto.setSlug(SlugUtils.toSlug(categoryRequestDto.getName()));
        }
        try {
            Category category = modelMapper.map(categoryRequestDto, Category.class);
            Category createdCategory = categoryService.createCategory(category);
            CategoryResponseDto categoryResponseDto = modelMapper.map(createdCategory, CategoryResponseDto.class);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", categoryResponseDto);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "success");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
