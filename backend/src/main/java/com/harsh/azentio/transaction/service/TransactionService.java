package com.harsh.azentio.transaction.service;

import com.harsh.azentio.transaction.dto.request.CreateTransactionRequest;
import com.harsh.azentio.transaction.entity.Transaction;

public interface TransactionService {

    Transaction createTransaction(CreateTransactionRequest request);
}