package com.api.elifeconnect.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import jakarta.annotation.PostConstruct;

@ConfigurationProperties(prefix = "spring.security.oauth2.client")
public class KeycloakClientsConfig {
    private static final Logger logger = LoggerFactory.getLogger(KeycloakClientsConfig.class);
    
    static {
        logger.info("KeycloakClientsConfig class is being loaded");
    }
    
    private Map<String, ClientConfig> registration = new HashMap<>();
    
    public KeycloakClientsConfig() {
        logger.info("KeycloakClientsConfig constructor called");
    }

    public Map<String, ClientConfig> getRegistration() {
        if (registration.isEmpty()) {
            logger.warn("No Keycloak client configurations are loaded!");
        } else {
            logger.debug("Currently loaded clients: {}", registration.keySet());
        }
        return registration;
    }

    public void setRegistration(Map<String, ClientConfig> registration) {
        logger.info("Loading {} Keycloak client configurations", registration.size());
        if (registration.isEmpty()) {
            logger.warn("Empty client configurations map provided!");
        }
        registration.forEach((key, config) -> {
            if (config == null) {
                logger.error("Client configuration for key '{}' is null!", key);
            } else {
                logger.info("Loaded client '{}' -> ID: {}, Grant Type: {}, Scopes: {}", 
                    key, 
                    config.getClientId(),
                    config.getAuthorizationGrantType(),
                    config.getScope());
            }
        });
        this.registration = registration;
    }

    public Optional<ClientConfig> getClient(String clientName) {
        logger.info("Attempting to get client with name: '{}'", clientName);
        logger.info("Current registration map: {}", registration);
        logger.info("Available client keys: {}", registration.keySet());
        
        if (!registration.containsKey(clientName)) {
            logger.warn("Client '{}' not found. Available clients: {}", clientName, registration.keySet());
            logger.warn("Note: Client names must match the keys in application.yaml. For example, use 'ui-client' instead of 'spring-ui-client'");
        }
        return Optional.ofNullable(registration.get(clientName));
    }

    @PostConstruct
    public void validateConfiguration() {
        logger.info("Validating Keycloak clients configuration...");
        if (registration.isEmpty()) {
            logger.warn("No Keycloak clients are configured! Check your application.yaml configuration.");
            return;
        }

        registration.forEach((key, config) -> {
            if (config == null) {
                logger.error("Client '{}' has null configuration!", key);
                return;
            }

            logger.info("Validating client '{}':", key);
            if (config.getClientId() == null || config.getClientId().isEmpty()) {
                logger.error("Client '{}' is missing clientId", key);
            }
            if (config.getClientSecret() == null || config.getClientSecret().isEmpty()) {
                logger.error("Client '{}' is missing clientSecret", key);
            }
            if (config.getAuthorizationGrantType() == null || config.getAuthorizationGrantType().isEmpty()) {
                logger.error("Client '{}' is missing authorizationGrantType", key);
            }
            if (config.getScope() == null || config.getScope().isEmpty()) {
                logger.warn("Client '{}' has no scopes defined", key);
            }
        });
    }
}