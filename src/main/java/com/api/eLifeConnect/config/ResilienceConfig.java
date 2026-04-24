package com.api.elifeconnect.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ResilienceConfig {

    @Bean
    public Retry retry() {
        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .retryExceptions(RuntimeException.class)
                .build();

        return Retry.of("apiRetry", retryConfig);
    }

    @Bean
    public CircuitBreaker circuitBreaker() {
        CircuitBreakerConfig cbConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)              // % of failures to open the circuit
                .slidingWindowSize(10)                 // number of calls to evaluate
                .waitDurationInOpenState(Duration.ofSeconds(20))
                .build();

        return CircuitBreaker.of("apiCB", cbConfig);
    }
}
