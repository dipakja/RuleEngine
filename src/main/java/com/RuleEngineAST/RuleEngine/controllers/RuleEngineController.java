package com.RuleEngineAST.RuleEngine.controllers;

import com.RuleEngineAST.RuleEngine.services.RuleEngineService;
import com.RuleEngineAST.RuleEngine.models.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/rules")
public class RuleEngineController {

    @Autowired
    private RuleEngineService ruleEngineService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createRule(@RequestBody Map<String, String> payload) {
        String rule = payload.get("rule");

        try {
            String createdRule = ruleEngineService.create_rule(rule);
            Map<String, String> response = new HashMap<>();
            response.put("rule", createdRule);  // Return the created rule
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }



    @PostMapping("/combine")
    public ResponseEntity<Map<String, String>> combineRules(@RequestBody Map<String, String> payload) {
        String rule1 = payload.get("rule1");
        String rule2 = payload.get("rule2");
        String operator = payload.get("operator");

        try {
            String combinedRule = ruleEngineService.combine_rules(rule1, rule2, operator);
            Map<String, String> response = new HashMap<>();
            response.put("combinedRule", combinedRule);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
