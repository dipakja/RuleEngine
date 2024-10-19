package com.RuleEngineAST.RuleEngine.services;

import com.RuleEngineAST.RuleEngine.models.Node;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RuleEngineServiceTest {

    private final RuleEngineService ruleEngineService = new RuleEngineService();
    @Test
    void testCreateRuleWithGreaterThanOperator() {
        String rule = ruleEngineService.create_rule("age > 30");
        assertNotNull(rule);
        assertEquals("age > 30", rule);
    }

    @Test
    void testCreateRuleWithEqualsOperator() {
        String rule = ruleEngineService.create_rule("salary = 5000");
        assertNotNull(rule);
        assertEquals("salary = 5000", rule);
    }

}
