package com.api.elifeconnect.repository;

import com.api.elifeconnect.entity.ExternalDocumentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExternalDocumentFileRepository extends JpaRepository<ExternalDocumentFile, Long> {

    List<ExternalDocumentFile> findByExternalUploadId(Long externalUploadId);

    List<ExternalDocumentFile> findByFileName(String fileName);
}
