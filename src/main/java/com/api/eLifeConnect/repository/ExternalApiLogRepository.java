package com.api.elifeconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.elifeconnect.entity.ExternalApiLog;

public interface ExternalApiLogRepository extends JpaRepository<ExternalApiLog, String> {}

