package com.api.elifeconnect.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "external_api_logs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExternalApiLog {

    @Id
    @Column(name = "reference_id", nullable = false, unique = true)
    private String referenceId;        // Primary Key from client request body

    private String apiName;
    private String httpMethod;
    private String url;

    @Lob
    private String requestPayload;

    @Lob
    private String requestHeaders;

    @Lob
    private String responsePayload;

    @Lob
    private String responseHeaders;

    private Integer httpStatus;
    private boolean success;

    @Lob
    private String errorMessage;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long durationMs;

    private LocalDateTime createdAt;
}
