package com.harsh.azentio.account.entity;

import com.harsh.azentio.customer.entity.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", nullable = false, unique = true, length = 50)
    private String accountNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private AccountType type;

    @Column(nullable = false, length = 10)
    private String currency;

    @Column(name = "opening_date", nullable = false)
    private LocalDate openingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "risk_rating", nullable = false, length = 20)
    private RiskRating riskRating;

    public enum AccountType {
        SAVINGS,
        CURRENT,
        BUSINESS
    }

    public enum RiskRating {
        LOW,
        MEDIUM,
        HIGH
    }
}