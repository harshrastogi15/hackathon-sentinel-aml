package com.harsh.azentio.transaction.controller;

import com.harsh.azentio.transaction.dto.request.CreateTransactionRequest;
import com.harsh.azentio.transaction.entity.Transaction;
import com.harsh.azentio.transaction.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction createTransaction(
            @Valid @RequestBody CreateTransactionRequest request) {

        return transactionService.createTransaction(request);
    }
}