package com.harsh.azentio.transaction.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateTransactionRequest(

        @NotBlank
        String transactionRef,

        @NotNull
        Long accountId,

        @NotNull
        @DecimalMin("0.01")
        BigDecimal amount,

        @NotBlank
        String currency,

        @NotBlank
        String type,

        @NotBlank
        String channel,

        @NotBlank
        String jurisdiction,

        @NotBlank
        String counterparty,

        @NotNull
        LocalDateTime transactionTime
) {
}