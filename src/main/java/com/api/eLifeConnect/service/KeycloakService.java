package com.api.elifeconnect.service;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.api.elifeconnect.config.ClientConfig;
import com.api.elifeconnect.config.KeycloakClientsConfig;
import com.api.elifeconnect.model.OAuthTokenResponse;
import com.api.elifeconnect.model.TokenResult;

@Service
public class KeycloakService {
    private static final Logger logger = LoggerFactory.getLogger(KeycloakService.class);
    private final KeycloakClientsConfig clientsConfig;
    private final String tokenUri;
    private final RestTemplate restTemplate;

    public KeycloakService(
            KeycloakClientsConfig clientsConfig,
            RestTemplate restTemplate,
            @Value("${spring.security.oauth2.client.provider.keycloak.token-uri}")
            String tokenUri) {
        this.clientsConfig = clientsConfig;
        this.tokenUri = tokenUri;
        this.restTemplate = restTemplate;
    }

    public TokenResult getToken(String clientName, String username, String password) {
        logger.debug("Attempting to get token for registration key: {}", clientName);
        
        ClientConfig clientConfig = clientsConfig.getClient(clientName)
                .orElseThrow(() -> new IllegalArgumentException("Unknown client: " + clientName));
                
        logger.info("Found client configuration - Registration Key: {}, Keycloak Client ID: {}", 
            clientName, clientConfig.getClientId());

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", clientConfig.getClientId());
        params.add("client_secret", clientConfig.getClientSecret());
        params.add("username", username);
        params.add("password", password);

        return requestToken(params);
    }

    public TokenResult getClientCredentialsToken(String clientName) {
        ClientConfig clientConfig = clientsConfig.getClient(clientName)
                .orElseThrow(() -> new IllegalArgumentException("Unknown client: " + clientName));

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "client_credentials");
        params.add("client_id", clientConfig.getClientId());
        params.add("client_secret", clientConfig.getClientSecret());

        return requestToken(params);
    }

    public TokenResult getAuthorizationCodeToken(String clientName, String code, String redirectUri) {
        ClientConfig clientConfig = clientsConfig.getClient(clientName)
                .orElseThrow(() -> new IllegalArgumentException("Unknown client: " + clientName));

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientConfig.getClientId());
        params.add("client_secret", clientConfig.getClientSecret());
        params.add("code", code);
        params.add("redirect_uri", redirectUri);

        return requestToken(params);
    }

    private TokenResult requestToken(MultiValueMap<String, String> params) {
    try {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        logger.debug("Sending token request to {}", tokenUri);
        logger.debug("Request parameters: {}", params);

        ResponseEntity<OAuthTokenResponse> response =
                restTemplate.postForEntity(tokenUri, request, OAuthTokenResponse.class);

        OAuthTokenResponse tokenResponse = response.getBody();
        if (tokenResponse == null) {
            logger.error("Failed to obtain token: No response body");
            return TokenResult.failure("no_response", "Keycloak returned an empty response body");
        }

        Instant expiresAt = Instant.now().plusSeconds(tokenResponse.getExpiresIn());

        logger.info("Token obtained. Expires at {}", expiresAt);

        return TokenResult.success(tokenResponse.getAccessToken(), expiresAt);

    } catch (RestClientException e) {
        logger.error("Failed to obtain token", e);

        // Provide meaningful error details
        return TokenResult.failure("request_failed", e.getMessage());
    }
}

}