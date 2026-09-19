package com.harsh.azentio.customer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "customers",
        indexes = {
                @Index(name = "idx_customer_ref", columnList = "customer_ref")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_ref", nullable = false, unique = true, length = 50)
    private String customerRef;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(name = "id_number", nullable = false, unique = true, length = 100)
    private String idNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "risk_rating", nullable = false, length = 20)
    private RiskRating riskRating;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public enum RiskRating {
        LOW,
        MEDIUM,
        HIGH
    }
}