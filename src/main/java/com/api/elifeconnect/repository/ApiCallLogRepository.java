package com.api.elifeconnect.repository;

import com.api.elifeconnect.entity.ApiCallLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiCallLogRepository extends JpaRepository<ApiCallLog, String> {

    // Optionally: find by clientId if needed later
    // List<ApiCallLog> findByClientId(String clientId);

    // Optionally: find by apiName
    // List<ApiCallLog> findByApiName(String apiName);

    // Optionally: find by referenceId (already primary key)
    // ApiCallLog findByReferenceId(String referenceId);
}
