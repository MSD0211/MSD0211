package com.api.elifeconnect.controller.kenya;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.elifeconnect.annotation.InDevelopment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@InDevelopment("Client mock endpoints under development")
@RestController
@RequestMapping("/api")
@Tag(name = "Client Specific Services", description = "Endpoints for Kenya client, web, and mobile context data")
public class ClientSpecificController {

    @GetMapping("/mobile/data")
    @PreAuthorize("hasAnyRole('MOBILE_USER', 'USER', 'ADMIN')")
    @Operation(summary = "Get Mobile Client Data", description = "Retrieves user and client data for mobile users.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Mobile data retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions")
    })
    public String getMobileData(@AuthenticationPrincipal Jwt jwt) {
        String clientId = jwt.getClaimAsString("azp");
        String username = jwt.getClaimAsString("preferred_username");
        return String.format("Mobile data for user %s from client %s", username, clientId);
    }

    @GetMapping("/web/data")
    @PreAuthorize("hasAnyRole('WEB_USER', 'ADMIN')")
    @Operation(summary = "Get Web Client Data", description = "Retrieves user and client data for web users.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Web data retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions")
    })
    public String getWebData(@AuthenticationPrincipal Jwt jwt) {
        String clientId = jwt.getClaimAsString("azp");
        String username = jwt.getClaimAsString("preferred_username");
        return String.format("Web data for user %s from client %s", username, clientId);
    }

    @GetMapping("/client-info")
    @Operation(summary = "Get Client Info", description = "Returns client ID, username, and realm roles.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Client info retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request")
    })
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