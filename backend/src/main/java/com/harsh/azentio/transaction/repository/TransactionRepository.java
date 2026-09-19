package com.harsh.azentio.transaction.repository;

import com.harsh.azentio.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountIdAndTransactionTimeBetween(
            Long accountId,
            LocalDateTime start,
            LocalDateTime end
    );

    List<Transaction> findByAccountCustomerIdAndTransactionTimeBetween(
            Long customerId,
            LocalDateTime start,
            LocalDateTime end
    );
}