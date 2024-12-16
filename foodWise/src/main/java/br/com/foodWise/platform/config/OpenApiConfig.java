package br.com.foodwise.platform.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI foodwiseOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info().title("Foodwise API")
                                .description("Foodwise API")
                                .version("1.0.0")
                                .license(new License().name("Apache 2.0").url("https://github.com/Postech-Code-Wizards/"))
                );
    }
}
