package com.example.practicespring.controller;

import com.example.practicespring.entity.RegisterDocuments;
import com.example.practicespring.service.RegistrationDocumentService;
import com.example.practicespring.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/files")
public class RegistrationDocumentsController {

    @Autowired
    private RegistrationDocumentService registrationDocumentService;

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/save/{registration_id}")
    public ResponseEntity<String> uploadDocument(@RequestParam Long registration_id,
                                                 @RequestParam("file") MultipartFile file,
                                                 @RequestParam("documentType") String documentType) {
        try {
            registrationDocumentService.uploadDocument(file, registration_id, documentType);
            return new ResponseEntity<>("Successfully uploaded the document", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to upload the file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RegisterDocuments> getDocumentById(@PathVariable Long id) {
        Optional<RegisterDocuments> registerDocuments = registrationDocumentService.getDocumentById(id);
        if (registerDocuments.isPresent()) {
            return new ResponseEntity<>(registerDocuments.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
