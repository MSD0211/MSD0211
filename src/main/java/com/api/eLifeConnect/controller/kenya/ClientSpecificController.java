package com.api.elifeconnect.controller.kenya;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClientSpecificController {

    @GetMapping("/mobile/data")
    @PreAuthorize("hasAnyRole('MOBILE_USER', 'USER', 'ADMIN')")
    public String getMobileData(@AuthenticationPrincipal Jwt jwt) {
        String clientId = jwt.getClaimAsString("azp");
        String username = jwt.getClaimAsString("preferred_username");
        return String.format("Mobile data for user %s from client %s", username, clientId);
    }

    @GetMapping("/web/data")
    @PreAuthorize("hasAnyRole('WEB_USER', 'ADMIN')")
    public String getWebData(@AuthenticationPrincipal Jwt jwt) {
        String clientId = jwt.getClaimAsString("azp");
        String username = jwt.getClaimAsString("preferred_username");
        return String.format("Web data for user %s from client %s", username, clientId);
    }

    @GetMapping("/client-info")
    public String getClientInfo(@AuthenticationPrincipal Jwt jwt) {
        String clientId = jwt.getClaimAsString("azp");
        String username = jwt.getClaimAsString("preferred_username");
        Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
        
        StringBuilder response = new StringBuilder();
        response.append("Client Information:\n");
        response.append("----------------\n");
        response.append(String.format("Client ID: %s\n", clientId));
        response.append(String.format("Username: %s\n", username));
        response.append("Roles: ").append(realmAccess != null ? realmAccess.get("roles") : "none");
        
        return response.toString();
    }
}