package com.api.elifeconnect.repository;

import com.api.elifeconnect.entity.ExternalDocumentUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExternalDocumentUploadRepository extends JpaRepository<ExternalDocumentUpload, Long> {

    List<ExternalDocumentUpload> findByReferenceId(String referenceId);

    List<ExternalDocumentUpload> findBySuccess(Boolean success);
}
