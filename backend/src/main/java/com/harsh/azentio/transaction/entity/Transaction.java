package com.harsh.azentio.transaction.entity;

import com.harsh.azentio.account.entity.Account;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "transactions",
        indexes = {
                @Index(name = "idx_tx_account_time",
                        columnList = "account_id, transaction_time"),
                @Index(name = "idx_tx_counterparty",
                        columnList = "counterparty")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_ref", nullable = false, unique = true)
    private String transactionRef;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(nullable = false, length = 10)
    private String currency;

    @Column(name = "base_amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal baseAmount;

    @Column(nullable = false, length = 30)
    private String type;

    @Column(nullable = false, length = 30)
    private String channel;

    @Column(nullable = false, length = 10)
    private String jurisdiction;

    @Column(nullable = false, length = 100)
    private String counterparty;

    @Column(name = "transaction_time", nullable = false)
    private LocalDateTime transactionTime;
}