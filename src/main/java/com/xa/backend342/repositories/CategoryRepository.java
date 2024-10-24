package com.xa.backend342.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xa.backend342.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    @Query("SELECT c FROM Category c WHERE c.slug = :slug")
    Optional<Category> getCategoryBySlug(@Param("slug") String slug);
}