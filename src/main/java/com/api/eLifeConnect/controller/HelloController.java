package com.api.elifeconnect.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class HelloController {

    @GetMapping("/api/public")
    @Operation(summary = "This is a Test Public Endpoint")
    public String publicEndpoint() {
        System.out.println("Public endpoint accessed!");
        return "This is a public endpoint — no token needed!";
    }

    @GetMapping("/api/user")
    // @PreAuthorize("hasRole('USER')")
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @Operation(summary = "This is a Test Private Endpoint for Users")
    public String userEndpoint(@AuthenticationPrincipal Jwt jwt) {
        return String.format("Hello %s! This is a USER endpoint. Your roles: %s",
            jwt.getClaimAsString("preferred_username"),
            jwt.getClaimAsMap("realm_access"));
    }

    @GetMapping("/api/manager")
    @PreAuthorize("hasAuthority('kenya.api.read')")
    @Operation(summary = "Test Private Endpoint for Managers")
    public String managerEndpoint(@AuthenticationPrincipal Jwt jwt) {
        return String.format("Hello Manager %s! This is a MANAGER endpoint. Your roles: %s",
            jwt.getClaimAsString("preferred_username"),
            jwt.getClaimAsMap("realm_access"));
    }

    @GetMapping("/api/admin")
     @PreAuthorize("hasAuthority('kenya.api.read')")
    @Operation(summary = "Test Private Endpoint for Admins")
    public String adminEndpoint(@AuthenticationPrincipal Jwt jwt) {
        return String.format("Hello Admin %s! This is an ADMIN endpoint. Your roles: %s",
            jwt.getClaimAsString("preferred_username"),
            jwt.getClaimAsMap("realm_access"));
    }

    @PostMapping("/api/content")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'CONTENT_CREATOR')")
    @Operation(summary = "Test Endpoint for Admin, Manager, or Content Creator to Create Content")
    public String createContent(@AuthenticationPrincipal Jwt jwt) {
        return String.format("User %s can create content. Roles: %s",
            jwt.getClaimAsString("preferred_username"),
            jwt.getClaimAsMap("realm_access"));
    }

    @DeleteMapping("/api/content")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public String deleteContent(@AuthenticationPrincipal Jwt jwt) {
        return String.format("User %s can delete content. Roles: %s",
            jwt.getClaimAsString("preferred_username"),
            jwt.getClaimAsMap("realm_access"));
    }
}
