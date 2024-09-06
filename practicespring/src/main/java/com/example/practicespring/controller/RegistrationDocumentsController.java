package com.example.practicespring.controller;

import com.example.practicespring.service.RegistrationDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
public class RegistrationDocumentsController {

    @Autowired
    private RegistrationDocumentService registrationDocumentService;

    @PostMapping("/upload/image")
    public ResponseEntity<String> uploadImageDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("registrationId") Long registrationId) {
        try {
            // Check if the file is an image
            String contentType = file.getContentType();
            if (contentType != null && contentType.startsWith("image/")) {
                // Extract text from the image
                String extractedText = registrationDocumentService.extractTextFromImage(file);

                // Save the document details and update the registration
                registrationDocumentService.uploadDocument(file, registrationId);

                return ResponseEntity.ok("Extracted text from image: " + extractedText);
            } else {
                return ResponseEntity.status(400).body("Unsupported file type: " + contentType);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading image document: " + e.getMessage());
        }
    }

    @PostMapping("/upload/pdf")
    public ResponseEntity<String> uploadPdfDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("registrationId") Long registrationId) {
        try {
            // Check if the file is a PDF
            String contentType = file.getContentType();
            if (contentType != null && contentType.equals("application/pdf")) {
                // Extract text from the PDF
                String extractedText = registrationDocumentService.extractTextFromPDF(file);

                // Save the document details and update the registration
                registrationDocumentService.uploadDocument(file, registrationId);

                return ResponseEntity.ok("Extracted text from PDF: " + extractedText);
            } else {
                return ResponseEntity.status(400).body("Unsupported file type: " + contentType);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading PDF document: " + e.getMessage());
        }
    }
}
