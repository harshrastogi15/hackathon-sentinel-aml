package com.harsh.azentio.detection.rule;

import com.harsh.azentio.detection.model.RuleResult;
import com.harsh.azentio.transaction.entity.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CtrRule implements DetectionRule {

    private static final BigDecimal THRESHOLD =
            new BigDecimal("10000");

    @Override
    public RuleResult evaluate(Transaction transaction) {

        if (transaction.getBaseAmount().compareTo(THRESHOLD) >= 0) {

            return new RuleResult(
                    true,
                    getRuleName(),
                    30,
                    "Transaction amount is at or above the reporting threshold."
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
        return "CTR_THRESHOLD";
    }
}