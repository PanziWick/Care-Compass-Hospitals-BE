package com.icbt.hospitalsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Apply to all endpoints
                        .allowedOriginPatterns("*") // Allow all origins
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Customize allowed methods
                        .allowedHeaders("*")
                        .exposedHeaders("Authorization")
                        .allowCredentials(true);; // Allow all headers
            }
        };
    }
}
