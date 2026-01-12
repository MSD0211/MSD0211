package com.api.elifeconnect.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiCallLog {

    @Id
    private String referenceId;

    private String apiName;
    private String httpMethod;
    private String url;
    private String clientId;
    private String clientIp;

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
