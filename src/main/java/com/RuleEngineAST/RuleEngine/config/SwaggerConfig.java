package com.RuleEngineAST.RuleEngine.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("rule-engine-api")
                .pathsToMatch("/api/v1/**") // adjust to your API paths
                .build();
    }
}
