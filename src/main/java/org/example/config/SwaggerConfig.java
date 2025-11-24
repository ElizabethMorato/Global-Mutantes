package org.example.config;
//configuraciones (OpenAPI/Swagger)

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiMutantes() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Detección de Mutantes")
                        .version("1.0")
                        .description("Servicio para detectar mutantes mediante secuencias de ADN"));
    }
}