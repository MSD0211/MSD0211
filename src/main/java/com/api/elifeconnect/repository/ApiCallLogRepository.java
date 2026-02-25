package com.api.elifeconnect.repository;

import com.api.elifeconnect.entity.ApiCallLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiCallLogRepository extends JpaRepository<ApiCallLog, String> {

    // Look up a log row by the requestId returned to the caller (support/debugging)
    Optional<ApiCallLog> findByRequestId(String requestId);

    // Optionally: find by business referenceId from the request body
    // List<ApiCallLog> findByReferenceId(String referenceId);
}
