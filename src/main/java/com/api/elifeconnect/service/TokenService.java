package com.api.elifeconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private OAuth2AuthorizedClientManager clientManager;

    public String getAccessToken() {
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId("keycloak")
                .principal("spring")
                .build();
        
        OAuth2AuthorizedClient authorizedClient = clientManager.authorize(authorizeRequest);
        if (authorizedClient == null) {
            throw new RuntimeException("Failed to obtain access token");
        }
        
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        if (accessToken == null) {
            throw new RuntimeException("No access token available");
        }
        
        return accessToken.getTokenValue();
    }
}