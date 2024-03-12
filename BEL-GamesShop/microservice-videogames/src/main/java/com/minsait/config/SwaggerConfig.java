package com.minsait.config;

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
                        .title("VideoGame API")
                        .description("API for managing video games")
                        .version("1.0.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentation for Springdoc OpenAPI")
                        .url("http://springdoc.org"));
    }

}
