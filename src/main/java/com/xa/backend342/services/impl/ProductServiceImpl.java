package com.xa.backend342.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xa.backend342.entities.Product;
import com.xa.backend342.repositories.ProductRepository;
import com.xa.backend342.services.interfaces.ProductService;
import com.xa.backend342.utils.SlugUtils;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        // JPA finds the entity by its ID
        // returns Optional util class
        // because entity by ID might not exists
        // Optional is a class with <T> as attribute
        // <T> can be empty/null, isPresent checks the <T> for it
        Optional<Product> existingProductOpt = productRepository.findById(id);
        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();
            existingProduct.setName(product.getName());
            if (product.getSlug() != null) {
                existingProduct.setSlug(product.getSlug());
            } else {
                existingProduct.setSlug(SlugUtils.toSlug(product.getName()));
            }
            existingProduct.setCreatedBy(product.getCreatedBy());
            existingProduct.setCategoryId(product.getCategoryId());
            return productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAvailableProductsByCategoryId(Long categoryId) {
        return productRepository.getAvailableProductsByCategoryId(categoryId);
    }

}
