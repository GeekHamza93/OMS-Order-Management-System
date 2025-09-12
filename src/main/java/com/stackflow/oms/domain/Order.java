// Order.java
package com.stackflow.oms.domain; // ou org.acme.oms.domain

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity @Table(name="orders")
public class Order extends PanacheEntity {
    @NotNull @Column(name="customer_id", nullable=false) public Long customerId;
    @NotNull @Enumerated(EnumType.STRING) @Column(name="status", length=30, nullable=false)
    public Status status = Status.CREATED;
    @NotNull @Min(0) @Column(name="amount", precision=14, scale=2, nullable=false) public BigDecimal amount;
    @Column(name="created_at", nullable=false) public Instant createdAt = Instant.now();
    public enum Status { CREATED, PAID, SHIPPED, CANCELED }
}