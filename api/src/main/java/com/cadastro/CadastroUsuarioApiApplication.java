package com.cadastro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Aplicação Spring Boot para API de Cadastro de Usuários
 * 
 * Esta aplicação fornece endpoints REST para:
 * - Cadastro de novos usuários
 * - Validação de dados
 * - Persistência em banco de dados H2
 */
@SpringBootApplication
public class CadastroUsuarioApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CadastroUsuarioApiApplication.class, args);
    }

    /**
     * Configuração de CORS para permitir requisições do frontend
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/usuarios/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(false)
                        .maxAge(3600);
            }
        };
    }
}
