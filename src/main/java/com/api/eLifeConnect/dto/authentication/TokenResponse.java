package com.api.elifeconnect.dto.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenResponse {
    private String token;
    private Integer expiresIn;
    private String error;

    public TokenResponse(String tokenOrError) {
        if (tokenOrError != null && tokenOrError.startsWith("Failed to get token:")) {
            this.error = tokenOrError;
        } else {
            this.token = tokenOrError;
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public Integer getExpiresIn() {
        return this.expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}