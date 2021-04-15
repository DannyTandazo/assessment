package com.dtandazo.devops.assessment.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${api.version}")
    private String apiVersion;

    @Value("${api.description}")
    private String apiDescription;

    @Value("${api.title}")
    private String title;

    @Value("${api.contact.email}")
    private String contactEmail;

    @Value("${api.contact.name}")
    private String contactName;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .version(apiVersion)
                        .description(apiDescription)
                        .contact(new Contact().email(contactEmail).name(contactName)));
    }
}
