package com.xa.backend342.entities;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "order_details")
@SQLDelete(sql = "UPDATE order_details SET is_deleted = true, deleted_at = NOW() WHERE id = ?")
public class OrderDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "header_id", nullable = false, insertable = false, updatable = false)
    private OrderHeader header;

    @Column(name = "header_id", nullable = false)
    private Long headerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "variant_id", nullable = false, insertable = false, updatable = false)
    private Variant variant;

    @Column(name = "variant_id", nullable = false)
    private Long variantId;

    @Column(name = "quantity", nullable = false)
    private BigDecimal quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public OrderDetail() {
    }

    public OrderDetail(Long headerId, Long variantId, BigDecimal quantity, BigDecimal price, String createdBy) {
        super();
        this.headerId = headerId;
        this.variantId = variantId;
        this.quantity = quantity;
        this.price = price;
        this.setCreatedBy(createdBy);
    }

    private static Log log = LogFactory.getLog(Category.class);

    @PreUpdate
    public void logOrderDetailUpdateAttempt() {
        log.info("Attempting to update Order Detail for product: " + variant.getName() + "on header: " + header.getReference());
    }
}
