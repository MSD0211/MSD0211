package com.api.elifeconnect.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.api.elifeconnect.annotation.InDevelopment;
import com.api.elifeconnect.dto.authentication.LoginRequest;
import com.api.elifeconnect.dto.authentication.TokenResponse;
import com.api.elifeconnect.model.TokenResult;
import com.api.elifeconnect.service.KeycloakService;

@InDevelopment("Authentication endpoints hidden from public documentation")
@RestController
@RequestMapping("/api")
@Tag(name = "Authentication Services", description = "Endpoints for OAuth2 and Keycloak token generation")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final KeycloakService keycloakService;

    public AuthController(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

   @PostMapping("/token")
   @Operation(summary = "Get User Token", description = "Generates a JWT token using password grant credentials.")
   @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
           @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Token generated successfully"),
           @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request or authentication failed")
   })
public ResponseEntity<TokenResult> getToken(@Valid @RequestBody LoginRequest loginRequest) {

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
  @Operation(summary = "Get Client Credentials Token", description = "Generates a token using OAuth2 client credentials grant for machine-to-machine authentication.")
  @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Client credentials token obtained successfully"),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Failed to obtain client credentials token")
  })
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
@Operation(summary = "Get Authorization Code Token", description = "Exchanges an authorization code for an OAuth2 access token.")
@io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Authorization code token obtained successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request or authorization failed")
})
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