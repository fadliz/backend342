package com.xa.backend342.entities;

import org.hibernate.annotations.SQLDelete;

import com.xa.backend342.utils.SlugUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "products")
@SQLDelete(sql = "UPDATE products SET is_deleted = true, deleted_at = NOW() WHERE id = ?")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "category_id", nullable = false, insertable = false, updatable = false)
    private Category category;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "slug", length = 50, nullable = false, unique = true)
    private String slug;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    public Product() {
    }

    public Product(Long categoryId, String name, String createdBy) {
        this.categoryId = categoryId;
        this.name = name;
        this.slug = SlugUtils.toSlug(name);
        this.setCreatedBy(createdBy);
    }

    // public Product(String name, String description, Long price, Long categoryId)
    // {
    // this.name = name;
    // this.slug = SlugUtils.toSlug(name);
    // this.description = description;
    // this.price = price;
    // this.categoryId = categoryId;
    // }
}
