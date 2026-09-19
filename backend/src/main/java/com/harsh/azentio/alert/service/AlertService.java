package com.harsh.azentio.alert.service;

import com.harsh.azentio.alert.entity.Alert;
import com.harsh.azentio.detection.model.RuleResult;
import com.harsh.azentio.transaction.entity.Transaction;

import java.util.List;

public interface AlertService {

    Alert createAlert(
            Transaction transaction,
            List<RuleResult> results
    );
}