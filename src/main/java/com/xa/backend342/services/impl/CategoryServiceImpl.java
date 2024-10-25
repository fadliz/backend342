package com.xa.backend342.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xa.backend342.dtos.requests.CategoryRequestDto;
import com.xa.backend342.dtos.responses.CategoryResponseDto;
import com.xa.backend342.entities.Category;
import com.xa.backend342.repositories.CategoryRepository;
import com.xa.backend342.services.interfaces.CategoryService;
import com.xa.backend342.utils.SlugUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) { // Generate the slug from name
        if (categoryRequestDto.getSlug() == null) {
            categoryRequestDto.setSlug(SlugUtil.toSlug(categoryRequestDto.getName()));
        }
        Category category = modelMapper.map(categoryRequestDto, Category.class);
        return modelMapper.map(categoryRepository.save(category), CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        CategoryResponseDto categoryResponseDto = modelMapper.map(category, CategoryResponseDto.class);
        return categoryResponseDto;
    }

    public CategoryResponseDto getCategoryBySlug(String slug) {
        Category category = categoryRepository.getCategoryBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        CategoryResponseDto categoryResponseDto = modelMapper.map(category, CategoryResponseDto.class);
        return categoryResponseDto;
    }

    @Override
    public List<CategoryResponseDto> getCategories() {
        List<Category> categorys = categoryRepository.findAll();
        List<CategoryResponseDto> categoryResponseDtos = categorys.stream()
                .map(category -> modelMapper.map(category, CategoryResponseDto.class))
                .collect(Collectors.toList());
        return categoryResponseDtos;
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto) {
        // JPA finds the entity by its ID
        // returns Optional util class
        // because entity by ID might not exists
        // Optional is a class with <T> as attribute
        // <T> can be empty/null, isPresent checks the <T> for it
        Optional<Category> existingCategoryOpt = categoryRepository.findById(id);
        if (existingCategoryOpt.isPresent()) {
            Category existingCategory = existingCategoryOpt.get();
            if (categoryRequestDto.getSlug() == null) {
                categoryRequestDto.setSlug(SlugUtil.toSlug(categoryRequestDto.getName()));
            }
            if (categoryRequestDto.getCreatedBy() == null) {
                categoryRequestDto.setCreatedBy(existingCategory.getCreatedBy());
            }
            modelMapper.map(categoryRequestDto, existingCategory);
            return modelMapper.map(categoryRepository.save(existingCategory), CategoryResponseDto.class);
        } else {
            throw new RuntimeException("Category not found");
        }
    }

    public CategoryResponseDto updateCategoryBySlug(String slug, CategoryRequestDto categoryRequestDto) {
        Optional<Category> existingCategoryOpt = categoryRepository.getCategoryBySlug(slug);
        if (existingCategoryOpt.isPresent()) {
            Category existingCategory = existingCategoryOpt.get();
            modelMapper.map(categoryRequestDto, existingCategory);
            if (categoryRequestDto.getSlug() == null) {
                existingCategory.setSlug(SlugUtil.toSlug(categoryRequestDto.getName()));
            }
            return modelMapper.map(categoryRepository.save(existingCategory), CategoryResponseDto.class);
        } else {
            throw new RuntimeException("Category not found");
        }
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public boolean isSlugUnique(String slug, Long id) {
        Optional<Category> existingCategoryOpt = categoryRepository.getCategoryBySlug(slug);
        if (existingCategoryOpt.isPresent()) {
            Category existingCategory = existingCategoryOpt.get();
            return existingCategory.getId().equals(id);
        }
        // If no category with the same slug exists, or it's the same entity being
        // updated
        return !existingCategoryOpt.isPresent();
    }
}
