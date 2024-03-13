package com.minsait.microservicecategories.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Categories API")
                        .description("Category API")
                        .version("0.0.1-SNAPSHOT"))
                .externalDocs(new ExternalDocumentation()
                        .description("springdoc-openapi")
                        .url("https://springdoc.org")
                );
    }

}