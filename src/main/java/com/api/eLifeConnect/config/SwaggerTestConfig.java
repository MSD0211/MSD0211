package com.api.elifeconnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;

@Configuration
@Profile("swagger-test")
public class SwaggerTestConfig {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new ClientRegistrationRepository() {
            @Override
            public ClientRegistration findByRegistrationId(String registrationId) {
                return null;
            }
        };
    }

    @Bean
    public OAuth2AuthorizedClientService oAuth2AuthorizedClientService() {
        return new OAuth2AuthorizedClientService() {
            @Override
            public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, String principalName) {
                return null;
            }

            @Override
            public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {
            }

            @Override
            public void removeAuthorizedClient(String clientRegistrationId, String principalName) {
            }
        };
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return new JwtDecoder() {
            @Override
            public Jwt decode(String token) throws JwtException {
                return null;
            }
        };
    }
}
