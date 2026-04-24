package com.api.elifeconnect.repository;

import com.api.elifeconnect.entity.DocumentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentFileRepository extends JpaRepository<DocumentFile, Long> {
    
    List<DocumentFile> findByUploadId(Long uploadId);
    
    List<DocumentFile> findByTag(String tag);
}
