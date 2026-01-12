package com.api.elifeconnect.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.elifeconnect.dto.authentication.LoginRequest;
import com.api.elifeconnect.dto.authentication.TokenResponse;
import com.api.elifeconnect.model.TokenResult;
import com.api.elifeconnect.service.KeycloakService;

@RestController
@RequestMapping("/api")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final KeycloakService keycloakService;

    public AuthController(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

   @PostMapping("/token")
public ResponseEntity<TokenResult> getToken(@RequestBody LoginRequest loginRequest) {

    logger.debug("Token request received for client: {}", loginRequest.getClientName());

    // Validation
    if (loginRequest == null ||
        loginRequest.getClientName() == null || loginRequest.getClientName().isBlank() ||
        loginRequest.getUsername() == null || loginRequest.getUsername().isBlank() ||
        loginRequest.getPassword() == null || loginRequest.getPassword().isBlank()) {

        logger.error("Invalid login request: {}", loginRequest);
        return ResponseEntity.badRequest().body(
                TokenResult.failure("invalid_request", "clientName, username, and password are required")
        );
    }

    // Call service
    TokenResult result = keycloakService.getToken(
            loginRequest.getClientName(),
            loginRequest.getUsername(),
            loginRequest.getPassword()
    );

    // Handle success/failure
    if (result.success()) {
        logger.debug("Token generated successfully for client: {}", loginRequest.getClientName());
        return ResponseEntity.ok(result);
    } else {
        logger.error("Token generation failed for client {}: {} - {}",
                loginRequest.getClientName(),
                result.error(),
                result.errorDescription());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}


  @PostMapping("/token/client-credentials")
public ResponseEntity<TokenResult> getClientCredentialsToken(@RequestParam String clientName) {

    logger.debug("Attempting to get client credentials token for client: {}", clientName);

    TokenResult result = keycloakService.getClientCredentialsToken(clientName);

    if (result.success()) {
        logger.debug("Successfully obtained client credentials token for client: {}", clientName);
        return ResponseEntity.ok(result);
    } else {
        logger.error("Failed to get token for client: {} - {} : {}", 
                     clientName, result.error(), result.errorDescription());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(result);   // return TokenResult containing error info
    }
}

@PostMapping("/token/oauth2/code")
public ResponseEntity<TokenResult> getAuthCodeToken(
        @RequestParam String clientName,
        @RequestParam String code,
        @RequestParam String redirectUri) {

    logger.debug("Token request (Authorization Code) received for client: {}", clientName);

    // Validate parameters
    if (clientName == null || clientName.isBlank() ||
        code == null || code.isBlank() ||
        redirectUri == null || redirectUri.isBlank()) {

        logger.error("Invalid authorization code request: clientName={}, code={}, redirectUri={}",
                clientName, code, redirectUri);

        return ResponseEntity.badRequest().body(
                TokenResult.failure("invalid_request", "clientName, code, and redirectUri are required")
        );
    }

    // Call service
    TokenResult result = keycloakService.getAuthorizationCodeToken(clientName, code, redirectUri);

    // Success response
    if (result.success()) {
        logger.debug("Authorization code token successfully obtained for client: {}", clientName);
        return ResponseEntity.ok(result);
    }

    // Failure response
    logger.error("Authorization code token failed for client {}: {} - {}",
            clientName, result.error(), result.errorDescription());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
}

}