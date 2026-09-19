package com.harsh.azentio.detection.rule;

import com.harsh.azentio.detection.model.RuleResult;
import com.harsh.azentio.transaction.entity.Transaction;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class HighRiskJurisdictionRule implements DetectionRule {

    private final Set<String> highRiskCountries =
            Set.of("XX", "YY", "ZZ");

    @Override
    public RuleResult evaluate(Transaction transaction) {

        if (highRiskCountries.contains(
                transaction.getJurisdiction().toUpperCase())) {

            return new RuleResult(
                    true,
                    getRuleName(),
                    50,
                    "Transaction involves a high-risk jurisdiction."
            );
        }

        return new RuleResult(
                false,
                getRuleName(),
                0,
                ""
        );
    }

    @Override
    public String getRuleName() {
        return "HIGH_RISK_JURISDICTION";
    }
}