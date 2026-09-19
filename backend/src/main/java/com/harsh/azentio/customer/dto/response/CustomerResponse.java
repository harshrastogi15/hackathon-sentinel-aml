package com.harsh.azentio.customer.dto.response;

import com.harsh.azentio.customer.entity.Customer.RiskRating;

import java.time.LocalDateTime;

public record CustomerResponse(
        Long id,
        String customerRef,
        String name,
        String idNumber,
        RiskRating riskRating,
        LocalDateTime createdAt
) {
}