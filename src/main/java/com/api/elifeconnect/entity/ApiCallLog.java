package com.api.elifeconnect.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "api_call_log")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiCallLog {

    @Id
    private String referenceId;

    private String apiName;
    private String apiShortName; // e.g. AGNTAUTH

    private String httpMethod;
    private String url;
    private String clientId;
    private String clientIp;

    /**
     * The requestId we returned in ApiResponse to the caller.
     * Allows end-to-end tracing when a caller raises a support ticket.
     */
    private String requestId;

    @Lob
    private String requestHeaders;

    @Lob
    private String requestPayload;

    @Lob
    private String responseHeaders;

    @Lob
    private String responsePayload;

    private Integer httpStatus;
    private Boolean success;
    private String errorMessage;

    private Instant startTime;
    private Instant endTime;
    private Long durationMs;
    private Instant createdAt;
}
