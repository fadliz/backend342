package com.xa.backend342.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xa.backend342.entities.Category;
import com.xa.backend342.repositories.CategoryRepository;
import com.xa.backend342.services.interfaces.CategoryService;
import com.xa.backend342.utils.SlugUtils;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) { // Generate the slug from name
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        // JPA finds the entity by its ID
        // returns Optional util class
        // because entity by ID might not exists
        // Optional is a class with <T> as attribute
        // <T> can be empty/null, isPresent checks the <T> for it
        Optional<Category> existingCategoryOpt = categoryRepository.findById(id);
        if (existingCategoryOpt.isPresent()) {
            Category existingCategory = existingCategoryOpt.get();
            if (category.getSlug() != null) {
                existingCategory.setSlug(category.getSlug());
            } else {
                existingCategory.setSlug(SlugUtils.toSlug(category.getName()));
            }
            existingCategory.setName(category.getName());
            existingCategory.setCreatedBy(category.getCreatedBy());
            return categoryRepository.save(existingCategory);
        } else {
            throw new RuntimeException("Category not found");
        }
    }

    public Category updateCategoryBySlug(String slug, Category category) {
        Optional<Category> existingCategoryOpt = categoryRepository.getCategoryBySlug(slug);
        if (existingCategoryOpt.isPresent()) {
            Category existingCategory = existingCategoryOpt.get();
            if (category.getSlug() != null) {
                existingCategory.setSlug(category.getSlug());
            } else {
                existingCategory.setSlug(SlugUtils.toSlug(category.getName()));
            }
            existingCategory.setName(category.getName());
            existingCategory.setCreatedBy(category.getCreatedBy());
            existingCategory.setDeleted(category.isDeleted());
            return categoryRepository.save(existingCategory);
        } else {
            throw new RuntimeException("Category not found");
        }
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id); 
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category getCategoryBySlug(String slug) {
        return categoryRepository.getCategoryBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public boolean isSlugUnique(String slug, Long id) {
        Optional<Category> existingCategoryOpt = categoryRepository.getCategoryBySlug(slug);
        if (existingCategoryOpt.isPresent()) {
            Category existingCategory = existingCategoryOpt.get();
            return existingCategory.getId().equals(id);
        } 
        // If no category with the same slug exists, or it's the same entity being updated
        return !existingCategoryOpt.isPresent();
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
