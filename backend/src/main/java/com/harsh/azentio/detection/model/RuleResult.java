package com.harsh.azentio.detection.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RuleResult {

    private boolean triggered;
    private String ruleName;
    private int score;
    private String explanation;
}