package com.emiteai.willian.core;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Documentation {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("EmiteAi")
                        .description("Teste para vaga de desenvolvedor Backend na empresa EmiteAI")
                        .version("v1.0.0"));
    }
}
