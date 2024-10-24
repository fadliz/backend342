package com.xa.backend342.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xa.backend342.dtos.requests.ProductRequestDto;
import com.xa.backend342.dtos.responses.ProductResponseDto;
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
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        if (productRequestDto.getSlug() == null) {
            productRequestDto.setSlug(SlugUtils.toSlug(productRequestDto.getName()));
        }
        Product product = modelMapper.map(productRequestDto, Product.class);
        return modelMapper.map(productRepository.save(product), ProductResponseDto.class);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        ProductResponseDto productResponseDto = modelMapper.map(product, ProductResponseDto.class);
        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> getProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> productResponseDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
        return productResponseDtos;
    }

    public List<ProductResponseDto> getAvailableProductsByCategoryId(Long categoryId) {
        List<Product> products = productRepository.getAvailableProductsByCategoryId(categoryId);
        List<ProductResponseDto> productResponseDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
        return productResponseDtos;
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        // JPA finds the entity by its ID
        // returns Optional util class
        // because entity by ID might not exists
        // Optional is a class with <T> as attribute
        // <T> can be empty/null, isPresent checks the <T> for it
        Optional<Product> existingProductOpt = productRepository.findById(id);
        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();
            if (productRequestDto.getSlug() == null) {
                productRequestDto.setSlug(SlugUtils.toSlug(productRequestDto.getName()));
            }
            if (productRequestDto.getCreatedBy() == null) {
                productRequestDto.setCreatedBy(existingProduct.getCreatedBy());
            }
            modelMapper.map(productRequestDto, existingProduct);
            return modelMapper.map(productRepository.save(existingProduct), ProductResponseDto.class);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
