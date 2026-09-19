package com.harsh.azentio.alert.service;

import com.harsh.azentio.alert.entity.Alert;
import com.harsh.azentio.alert.repository.AlertRepository;
import com.harsh.azentio.detection.model.RuleResult;
import com.harsh.azentio.transaction.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository;

    @Override
    public Alert createAlert(
            Transaction transaction,
            List<RuleResult> results) {

        if (results.isEmpty()) {
            return null;
        }

        int score = Math.min(
                100,
                results.stream()
                        .mapToInt(RuleResult::getScore)
                        .sum()
        );

        String rules = results.stream()
                .map(RuleResult::getRuleName)
                .reduce((a, b) -> a + "," + b)
                .orElse("");

        String explanation = results.stream()
                .map(RuleResult::getExplanation)
                .reduce((a, b) -> a + " " + b)
                .orElse("");

        Alert alert = Alert.builder()
                .customer(transaction.getAccount().getCustomer())
                .riskScore(score)
                .status("OPEN")
                .triggeredRules(rules)
                .explanation(explanation)
                .build();

        return alertRepository.save(alert);
    }
}