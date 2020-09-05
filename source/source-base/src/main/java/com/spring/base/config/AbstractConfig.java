package com.spring.base.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;

public abstract class AbstractConfig {

    public static final String _SecurityScheme_Basic = "Basic";
    public static final String _SecurityScheme_Bearer = "JWT-Bearer";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(
                        new Components()
                                .addSecuritySchemes(_SecurityScheme_Basic,
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("Basic")
                                                .description("Security Scheme Basic Authentication"))
                                .addSecuritySchemes(_SecurityScheme_Bearer,
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                                .description("Security Scheme Authorization Token JWT"))
                );
    }
}