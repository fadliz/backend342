package com.xa.backend342.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xa.backend342.entities.Product;
import com.xa.backend342.entities.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
    @Query("SELECT v FROM Variant v WHERE v.product.isDeleted = FALSE AND v.product.category.isDeleted = FALSE")
    List<Variant> getAvailableVariants();

    @Query("SELECT v FROM Variant v WHERE v.product.id = :productId AND v.product.isDeleted = FALSE")
    List<Product> getAvailableVariantsByProductId(@Param("productId") Long productId);

}
