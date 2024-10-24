package com.xa.backend342.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {

    @Column(name = "create_by", length = 50, nullable = false)
    private String createdBy;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_by", length = 50, nullable = true)
    private String modifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at", nullable = true)
    private LocalDateTime modifiedAt;
    
    @Column(name = "is_deleted", nullable = false/* , columnDefinition="bit" */)
    private boolean isDeleted = false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;

    @PreUpdate
    public void onUpdate() {
        this.modifiedAt = LocalDateTime.now();
    }


    // @CreationTimestamp
    // @Temporal(TemporalType.TIMESTAMP)
    // @Column(name = "created_at")
    // private LocalDateTime createdAt;

    // @UpdateTimestamp
    // @Temporal(TemporalType.TIMESTAMP)
    // @Column(name = "updated_at")
    // private LocalDateTime updatedAt;
}
