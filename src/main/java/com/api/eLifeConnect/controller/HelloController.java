package com.api.elifeconnect.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.elifeconnect.annotation.InDevelopment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@InDevelopment("Role testing endpoints under development")
@RestController
@Tag(name = "Testing & Samples", description = "Endpoints for testing role-based access control and public connectivity")
public class HelloController {

    @GetMapping("/api/public")
    @Operation(summary = "This is a Test Public Endpoint", description = "Sample public endpoint accessible without authentication.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Public endpoint accessed successfully")
    })
    public String publicEndpoint() {
        System.out.println("Public endpoint accessed!");
        return "This is a public endpoint — no token needed!";
    }

    @GetMapping("/api/user")
    // @PreAuthorize("hasRole('USER')")
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @Operation(summary = "This is a Test Private Endpoint for Users", description = "Role-based test endpoint for standard authenticated users.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User endpoint accessed successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions")
    })
    public String userEndpoint(@AuthenticationPrincipal Jwt jwt) {
        return String.format("Hello %s! This is a USER endpoint. Your roles: %s",
            jwt.getClaimAsString("preferred_username"),
            jwt.getClaimAsMap("realm_access"));
    }

    @GetMapping("/api/manager")
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @Operation(summary = "Test Private Endpoint for Managers", description = "Role-based test endpoint for manager-level users.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Manager endpoint accessed successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions")
    })
    public String managerEndpoint(@AuthenticationPrincipal Jwt jwt) {
        return String.format("Hello Manager %s! This is a MANAGER endpoint. Your roles: %s",
            jwt.getClaimAsString("preferred_username"),
            jwt.getClaimAsMap("realm_access"));
    }

    @GetMapping("/api/admin")
     @PreAuthorize("hasAuthority('kenya.api.read')")
    @Operation(summary = "Test Private Endpoint for Admins", description = "Role-based test endpoint for administrator users.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Admin endpoint accessed successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions")
    })
    public String adminEndpoint(@AuthenticationPrincipal Jwt jwt) {
        return String.format("Hello Admin %s! This is an ADMIN endpoint. Your roles: %s",
            jwt.getClaimAsString("preferred_username"),
            jwt.getClaimAsMap("realm_access"));
    }

    @PostMapping("/api/content")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'CONTENT_CREATOR')")
    @Operation(summary = "Test Endpoint for Admin, Manager, or Content Creator to Create Content", description = "Role-based test endpoint to create content.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Content created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions")
    })
    public String createContent(@AuthenticationPrincipal Jwt jwt) {
        return String.format("User %s can create content. Roles: %s",
            jwt.getClaimAsString("preferred_username"),
            jwt.getClaimAsMap("realm_access"));
    }

    @DeleteMapping("/api/content")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @Operation(summary = "Test Endpoint for Admin or Manager to Delete Content", description = "Role-based test endpoint for deleting content.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Content deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions")
    })
    public String deleteContent(@AuthenticationPrincipal Jwt jwt) {
        return String.format("User %s can delete content. Roles: %s",
            jwt.getClaimAsString("preferred_username"),
            jwt.getClaimAsMap("realm_access"));
    }
}
