package com.api.elifeconnect.repository;

import com.api.elifeconnect.entity.DocumentUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentUploadRepository extends JpaRepository<DocumentUpload, Long> {

    Optional<DocumentUpload> findByReferenceId(String referenceId);
}
