package com.api.elifeconnect.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(KeycloakClientsConfig.class)
public class KeycloakConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(KeycloakConfiguration.class);
    
    private final KeycloakClientsConfig clientsConfig;
    
    public KeycloakConfiguration(KeycloakClientsConfig clientsConfig) {
        this.clientsConfig = clientsConfig;
        logger.info("KeycloakConfiguration initialized");
        logger.info("Available clients: {}", 
            clientsConfig.getRegistration() != null ? clientsConfig.getRegistration().keySet() : "null");
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}