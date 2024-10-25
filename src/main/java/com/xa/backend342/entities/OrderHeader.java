package com.xa.backend342.entities;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "order_headers")
@SQLDelete(sql = "UPDATE order_headers SET is_deleted = true, deleted_at = NOW() WHERE id = ?")
public class OrderHeader extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference", length = 15, nullable = false)
    private String reference;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    public OrderHeader() {
    }

    public OrderHeader(String reference, BigDecimal amount) {
        super();
        this.reference = reference;
        this.amount = amount;
    }

    private static Log log = LogFactory.getLog(Category.class);

    @PreUpdate
    public void logOrderHeaderUpdateAttempt() {
        log.info("Attempting to update Order Header reference: " + reference);
    }
}
