package com.RuleEngineAST.RuleEngine.services;

import com.RuleEngineAST.RuleEngine.models.Node;
import com.RuleEngineAST.RuleEngine.models.Rule;
import com.RuleEngineAST.RuleEngine.repositories.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
public class RuleEngineService {

    @Autowired
    private RuleRepository ruleRepository;

    public String create_rule(String rule) {
        Node root = parseRuleToAST(rule);
        saveRuleToDatabase(rule); // Save the rule to the database
        return nodeToString(root);
    }
    private String nodeToString(Node node) {
        if (node == null) return "";
        if (node.getLeft() == null && node.getRight() == null) {
            return node.getValue();  // Return field or value directly for leaf nodes
        }
        return nodeToString(node.getLeft()) + " " + node.getValue() + " " + nodeToString(node.getRight());
    }
    public Node parseRuleToAST(String rule) {
        String operator = null;

        // Identify the operator in the rule
        if (rule.contains(">")) {
            operator = ">";
        } else if (rule.contains("=")) {
            operator = "=";
        } else {
            throw new IllegalArgumentException("Invalid rule format: " + rule);
        }

        // Split based on the identified operator
        String[] parts = rule.split(operator);
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid rule format: " + rule);
        }

        String field = parts[0].trim();
        String value = parts[1].trim();

        if (field.isEmpty() || value.isEmpty()) {
            throw new IllegalArgumentException("Invalid rule format: " + rule);
        }

        Node leftNode = new Node(field);  // Left node for the field (e.g., age, experience)
        Node rightNode = new Node(value); // Right node for the value (e.g., 30, 2)

        return new Node(operator, leftNode, rightNode); // Return the node with the identified operator
    }

    public String combine_rules(String rule1, String rule2, String operator) {
        if (!operator.equals("AND") && !operator.equals("OR")) {
            throw new IllegalArgumentException("Operator must be 'AND' or 'OR'");
        }
        return rule1 + " " + operator + " " + rule2;
    }

    private void saveRuleToDatabase(String rule) {
        Rule newRule = new Rule();
        newRule.setRule(rule);
        newRule.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        ruleRepository.save(newRule); // Save to the database
    }

    public boolean evaluate_rule(Node ast, Map<String, Object> data) {
        return evaluateAST(ast, data);
    }

    private boolean evaluateAST(Node ast, Map<String, Object> data) {
        String rule = ast.getRule();
        return evaluateCondition(rule, data);
    }

    private boolean evaluateCondition(String rule, Map<String, Object> data) {
        String[] parts = rule.split("=");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Condition format is incorrect: " + rule);
        }

        String field = parts[0];
        String valueStr = parts[1];

        Object fieldValue = data.get(field);
        if (fieldValue == null) {
            throw new IllegalArgumentException("Field not found in data: " + field);
        }

        int fieldValueInt = (Integer) fieldValue;
        int value = Integer.parseInt(valueStr.trim());

        return fieldValueInt > value; // Adjust this logic based on your needs (>, <, ==, etc.)
    }
}
