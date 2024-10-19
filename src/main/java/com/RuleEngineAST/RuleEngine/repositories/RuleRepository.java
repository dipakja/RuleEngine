package com.RuleEngineAST.RuleEngine.repositories;

import com.RuleEngineAST.RuleEngine.models.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long> {
}