package com.example.practicespring.service;

import org.springframework.web.multipart.MultipartFile;

public interface RegistrationDocumentService {

    // Method to handle image file upload and text extraction
    String extractTextFromImage(MultipartFile file);

    // Method to handle PDF file upload and text extraction
    String extractTextFromPDF(MultipartFile file);

    // Method to upload document details and update registration status
    String uploadDocument(MultipartFile file, Long registrationId);
}
