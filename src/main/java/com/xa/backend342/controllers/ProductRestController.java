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

import com.xa.backend342.dtos.requests.ProductRequestDto;
import com.xa.backend342.dtos.responses.ProductResponseDto;
import com.xa.backend342.entities.Product;
import com.xa.backend342.services.impl.ProductServiceImpl;
import com.xa.backend342.utils.SlugUtils;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("http://localhost:9002")
public class ProductRestController {

    @Autowired
    ProductServiceImpl productService;

    @GetMapping("")
    public ResponseEntity<?> getProducts() {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        try {
            List<Product> products = productService.getProducts();
            List<ProductResponseDto> productResponseDtos = products.stream()
                    .map(product -> modelMapper.map(product, ProductResponseDto.class))
                    .collect(Collectors.toList());
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", productResponseDtos);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "success");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getAvailableProductsByCategoryId(@PathVariable Long categoryId) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        try {
            List<Product> products = productService.getAvailableProductsByCategoryId(categoryId);
            List<ProductResponseDto> productResponseDtos = products.stream()
                    .map(product -> modelMapper.map(product, ProductResponseDto.class))
                    .collect(Collectors.toList());
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", productResponseDtos);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "success");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        try {
            Product product = productService.getProduct(id);
            ProductResponseDto productResponseDto = modelMapper.map(product, ProductResponseDto.class);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", productResponseDto);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "success");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,
            @RequestBody ProductRequestDto productRequestDto) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        if (productRequestDto.getSlug() == null) {
            productRequestDto.setSlug(SlugUtils.toSlug(productRequestDto.getName()));
        }
        try {
            Product product = modelMapper.map(productRequestDto, Product.class);
            Product updatedProduct = productService.updateProduct(id, product);
            ProductResponseDto productResponseDto = modelMapper.map(updatedProduct, ProductResponseDto.class);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", productResponseDto);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "success");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity<?> saveProduct(@RequestBody ProductRequestDto productRequestDto) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        if (productRequestDto.getSlug() == null) {
            productRequestDto.setSlug(SlugUtils.toSlug(productRequestDto.getName()));
        }
        try {
            Product product = modelMapper.map(productRequestDto, Product.class);
            Product createdProduct = productService.createProduct(product);
            ProductResponseDto productResponseDto = modelMapper.map(createdProduct, ProductResponseDto.class);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", productResponseDto);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "success");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
