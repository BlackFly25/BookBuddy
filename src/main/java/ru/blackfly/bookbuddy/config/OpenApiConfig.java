package ru.blackfly.bookbuddy.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI bookBuddyOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("📚 BookBuddy API")
                        .description("Учебное REST-приложение для управления книгами, пользователями и отзывами")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("BlackFly")
                                .email("support@bookbuddy.ru"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                );
    }
    }

