package com.harsh.azentio.detection.rule;

import com.harsh.azentio.detection.model.RuleResult;
import com.harsh.azentio.transaction.entity.Transaction;

public interface DetectionRule {

    RuleResult evaluate(Transaction transaction);

    String getRuleName();
}