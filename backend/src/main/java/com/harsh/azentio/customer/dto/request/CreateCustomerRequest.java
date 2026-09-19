package com.harsh.azentio.customer.dto.request;

import com.harsh.azentio.customer.entity.Customer.RiskRating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCustomerRequest(

        @NotBlank
        @Size(max = 50)
        String customerRef,

        @NotBlank
        @Size(max = 150)
        String name,

        @NotBlank
        @Size(max = 100)
        String idNumber,

        @NotNull
        RiskRating riskRating
) {
}