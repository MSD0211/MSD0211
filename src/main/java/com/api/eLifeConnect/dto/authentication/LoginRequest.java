package com.api.elifeconnect.dto.authentication;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Credentials for obtaining OAuth2 / Keycloak token")
public class LoginRequest {
    @NotBlank(message = "clientName is required")
    @Schema(description = "OAuth2 registered client identifier", example = "ui-client")
    private String clientName;

    @NotBlank(message = "username is required")
    @Schema(description = "User or service account username", example = "john.doe")
    private String username;

    @NotBlank(message = "password is required")
    @Schema(description = "Account password", example = "P@ssw0rd123!")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}