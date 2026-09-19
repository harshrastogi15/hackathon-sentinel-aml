package com.harsh.azentio.detection.engine;

import com.harsh.azentio.detection.model.RuleResult;
import com.harsh.azentio.detection.rule.DetectionRule;
import com.harsh.azentio.transaction.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetectionEngine {

    private final List<DetectionRule> rules;

    public List<RuleResult> evaluate(Transaction transaction) {

        return rules.stream()
                .map(rule -> rule.evaluate(transaction))
                .filter(RuleResult::isTriggered)
                .toList();
    }
}