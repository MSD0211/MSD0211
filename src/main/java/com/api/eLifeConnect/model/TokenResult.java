package com.api.elifeconnect.model;

import java.time.Instant;

public record TokenResult(
        boolean success,
        String accessToken,
        Instant expiresAt,
        String error,
        String errorDescription
) {
    public static TokenResult success(String token, Instant expiresAt) {
        return new TokenResult(true, token, expiresAt, null, null);
    }

    public static TokenResult failure(String error, String description) {
        return new TokenResult(false, null, null, error, description);
    }
}
