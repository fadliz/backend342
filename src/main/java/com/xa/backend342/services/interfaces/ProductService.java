package com.xa.backend342.services.interfaces;

import java.util.List;

import com.xa.backend342.entities.Product;

public interface ProductService {
    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    Product getProduct(Long id);

    List<Product> getProducts();
}
