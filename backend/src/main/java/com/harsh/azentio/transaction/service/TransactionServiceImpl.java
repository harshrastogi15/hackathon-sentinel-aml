package com.harsh.azentio.transaction.service;

import com.harsh.azentio.account.entity.Account;
import com.harsh.azentio.account.repository.AccountRepository;
import com.harsh.azentio.alert.service.AlertService;
import com.harsh.azentio.detection.engine.DetectionEngine;
import com.harsh.azentio.detection.model.RuleResult;
import com.harsh.azentio.transaction.dto.request.CreateTransactionRequest;
import com.harsh.azentio.transaction.entity.Transaction;
import com.harsh.azentio.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final DetectionEngine detectionEngine;
    private final AlertService alertService;

    @Override
    @Transactional
    public Transaction createTransaction(CreateTransactionRequest request) {

        Account account = accountRepository.findById(request.accountId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Account not found"));

        Transaction transaction = Transaction.builder()
                .transactionRef(request.transactionRef())
                .account(account)
                .amount(request.amount())
                .currency(request.currency().toUpperCase())
                .baseAmount(calculateBaseAmount(request))
                .type(request.type().toUpperCase())
                .channel(request.channel().toUpperCase())
                .jurisdiction(request.jurisdiction().toUpperCase())
                .counterparty(request.counterparty())
                .transactionTime(request.transactionTime())
                .build();

        Transaction saved = transactionRepository.save(transaction);
        List<RuleResult> resultList = detectionEngine.evaluate(saved);
        if(!resultList.isEmpty()){
            alertService.createAlert(saved, resultList);
        }
        return saved;
    }

    private BigDecimal calculateBaseAmount(
            CreateTransactionRequest request) {

        // For MVP, INR is the base currency.
        // Exchange-rate service will replace this later.
        if ("INR".equalsIgnoreCase(request.currency())) {
            return request.amount();
        }

        // Temporary assumption for prototype.
        return request.amount();
    }
}