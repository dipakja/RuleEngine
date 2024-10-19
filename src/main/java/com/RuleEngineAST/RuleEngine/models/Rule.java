package com.RuleEngineAST.RuleEngine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "rules")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String rule;

    @CreationTimestamp // Automatically generate timestamp when the row is created
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
