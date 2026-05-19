package com.tech_centriq.busservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI busServiceOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("GoMzansi Bus Service API")
                        .description("Bus management API for GoMzansi Transport Information System")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Sizwe Ncikana")
                                .email("sizwe.ncikana@gomzansi.co.za")
                                .url("https://www.gomzansi.co.za")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                        )
                );
    }

}
