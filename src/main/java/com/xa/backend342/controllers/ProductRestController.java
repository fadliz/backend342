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

import com.xa.backend342.dtos.requests.ProductRequestDto;
import com.xa.backend342.dtos.responses.ProductResponseDto;
import com.xa.backend342.payloads.ApiResponse;
import com.xa.backend342.services.impl.ProductServiceImpl;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("http://localhost:9002")
public class ProductRestController {

    @Autowired
    ProductServiceImpl productService;

    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto productResponseDto = productService.createProduct(productRequestDto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), productResponseDto));
    }

    @GetMapping("")
    public ResponseEntity<?> getProducts() {
        List<ProductResponseDto> productResponseDtos = productService.getProducts();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), productResponseDtos));

    }

    @GetMapping("/categoryId={categoryId}")
    public ResponseEntity<?> getAvailableProductsByCategoryId(@PathVariable Long categoryId) {
        List<ProductResponseDto> productResponseDtos = productService.getAvailableProductsByCategoryId(categoryId);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), productResponseDtos));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        ProductResponseDto productResponseDto = productService.getProductById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), productResponseDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,
            @RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto productResponseDto = productService.updateProduct(id, productRequestDto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), productResponseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
