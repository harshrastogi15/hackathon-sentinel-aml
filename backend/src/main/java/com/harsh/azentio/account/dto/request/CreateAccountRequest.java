package com.harsh.azentio.account.dto.request;

import com.harsh.azentio.account.entity.Account.AccountType;
import com.harsh.azentio.account.entity.Account.RiskRating;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CreateAccountRequest(

        @NotBlank
        String accountNumber,

        @NotNull
        Long customerId,

        @NotNull
        AccountType type,

        @NotBlank
        @Size(min = 3, max = 10)
        String currency,

        @NotNull
        LocalDate openingDate,

        @NotNull
        RiskRating riskRating
) {
}