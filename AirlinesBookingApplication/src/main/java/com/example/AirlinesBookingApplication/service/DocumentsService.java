package com.example.AirlinesBookingApplication.service;

import com.example.AirlinesBookingApplication.entity.Documents;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface DocumentsService {
    void saveDocument(MultipartFile file, Long userId, String documentType) throws Exception;
    Optional<Documents> getDocumentById(Long id);
}
