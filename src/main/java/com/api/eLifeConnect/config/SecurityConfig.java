package com.api.elifeconnect.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {
    // // Disable CSRF as we're using token-based authentication
    // http.csrf(csrf -> csrf.disable());

    // // Configure authorization
    // http.authorizeHttpRequests(auth -> auth
    // // Public endpoints that don't require authentication
    // .requestMatchers("/api/public", "/api/public/**").permitAll()
    // .requestMatchers("/api/token", "/api/token/**").permitAll()
    // .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
    // // Client-specific endpoints
    // // .requestMatchers("/api/mobile/**").hasAnyRole("MOBILE_USER", "ADMIN")
    // // .requestMatchers("/api/web/**").hasAnyRole("WEB_USER", "ADMIN")
    // // All other endpoints require authentication
    // .anyRequest().authenticated()
    // );

    // // Configure JWT resource server with support for multiple clients
    // http.oauth2ResourceServer(oauth2 -> oauth2
    // .jwt(jwt -> jwt
    // .jwtAuthenticationConverter(jwtAuthenticationConverter())
    // )
    // );

    // return http.build();
    // }

    // @Bean
    // public JwtAuthenticationConverter jwtAuthenticationConverter() {
    // JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    // converter.setJwtGrantedAuthoritiesConverter(jwt -> {
    // // Convert realm_access.roles to Spring Security authorities
    // Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
    // if (realmAccess == null) {
    // return java.util.Collections.emptyList();
    // }

    // @SuppressWarnings("unchecked")
    // Collection<String> roles = (Collection<String>) realmAccess.get("roles");
    // if (roles == null) {
    // return java.util.Collections.emptyList();
    // }

    // return roles.stream()
    // .map(roleName -> "ROLE_" + roleName.toUpperCase())
    // .map(SimpleGrantedAuthority::new)
    // .collect(Collectors.toList());
    // });
    // return converter;
    // }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth -> oauth
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(keycloakJwtAuthenticationConverter())));

        return http.build();
    }

    @Bean
    JwtAuthenticationConverter keycloakJwtAuthenticationConverter() {

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Set<GrantedAuthority> authorities = new HashSet<>();

            Map<String, Object> resourceAccess = jwt.getClaim("resource_access");

            if (resourceAccess != null) {
                Object client = resourceAccess.get("api-client");

                if (client instanceof Map<?, ?> clientMap) {/*  */
                    Object roles = clientMap.get("roles");

                    if (roles instanceof Collection<?> roleList) {
                        for (Object role : roleList) {
                            authorities.add(
                                    new SimpleGrantedAuthority(role.toString()));
                        }
                    }
                }
            }

            return authorities;
        });

        return converter;
    }

}
