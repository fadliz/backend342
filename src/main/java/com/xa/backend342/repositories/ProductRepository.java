package com.xa.backend342.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xa.backend342.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.category.isDeleted = FALSE")
    List<Product> getAvailableProducts();

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId AND p.category.isDeleted = FALSE")
    List<Product> getAvailableProductsByCategoryId(@Param("categoryId") Long categoryId);
}
