package com.example.news.configs;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
@ConditionalOnProperty(value = "springdoc.api-docs.enabled", havingValue = "true")
public class DischargeServiceConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        String securityKey = "apiKey";
        return new OpenAPI()
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        securityKey,
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.APIKEY)
                                                .in(SecurityScheme.In.HEADER)
                                                .name(HttpHeaders.AUTHORIZATION)))
                .info(new Info().title("OfferHub publisher API doc"))
                .addSecurityItem(new SecurityRequirement().addList(securityKey));
    }
}
