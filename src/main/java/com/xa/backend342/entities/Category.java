package com.xa.backend342.entities;

import org.hibernate.annotations.SQLDelete;

import com.xa.backend342.utils.SlugUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "categories")
@SQLDelete(sql = "UPDATE categories SET is_deleted = true, deleted_at = NOW() WHERE id = ?")
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "slug", length = 50, nullable = false, unique = true)
    private String slug;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    public Category() {

    }

    public Category(String name, String createdBy) {
        this.name = name;
        this.slug = SlugUtils.toSlug(name);
        this.setCreatedBy(createdBy);
    }

}
