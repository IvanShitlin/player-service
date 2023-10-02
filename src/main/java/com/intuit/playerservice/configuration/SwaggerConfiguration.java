package com.intuit.playerservice.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI myAPI() {
        return new OpenAPI()
                .info(new Info()
                        .summary("Players Service for Intuit")
                        .title("Players Service API")
                        .version("1.0"));
    }
}
