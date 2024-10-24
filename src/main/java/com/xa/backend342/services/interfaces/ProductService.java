package com.xa.backend342.services.interfaces;

import java.util.List;

import com.xa.backend342.dtos.requests.ProductRequestDto;
import com.xa.backend342.dtos.responses.ProductResponseDto;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);

    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);

    void deleteProduct(Long id);

    ProductResponseDto getProductById(Long id);

    List<ProductResponseDto> getProducts();
}