package com.example.practicespring.service;

import com.example.practicespring.entity.RegisterDocuments;
import com.example.practicespring.entity.Registration;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface RegistrationDocumentService
{

    void uploadDocument(MultipartFile file,Long registration_id, String documentType);

    Optional<RegisterDocuments> getDocumentById(Long id);
}
