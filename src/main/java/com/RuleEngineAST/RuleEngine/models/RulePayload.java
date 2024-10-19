package com.RuleEngineAST.RuleEngine.models;

import java.util.Map;

public class RulePayload {
    private String rule;
    private Map<String, Object> data;

    // Getters and Setters
    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
