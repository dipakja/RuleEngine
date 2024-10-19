package com.RuleEngineAST.RuleEngine.models;

public class Node {
    private String operator; // AND, OR, etc.
    private Node left; // Left child
    private Node right; // Right child
    private String field; // Operand field name
    private String value; // Operand value

    // Constructor for operands
    public Node(String field, String value) {
        this.field = field;
        this.value = value;
    }

    // Constructor for operators
    public Node(String operator, Node left, Node right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public Node(String field) {
        this.field = field;
    }

    // Getters
    public String getOperator() {
        return operator;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }

    public String getRule() {
        // Return the rule as a formatted string
        if (operator != null) {
            return left.getRule() + " " + operator + " " + right.getRule();
        }
        return field + " > " + value; // Adjust based on your needs
    }
}
