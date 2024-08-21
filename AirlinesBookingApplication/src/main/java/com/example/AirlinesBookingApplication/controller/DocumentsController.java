package com.example.AirlinesBookingApplication.controller;

import com.example.AirlinesBookingApplication.entity.Documents;
import com.example.AirlinesBookingApplication.service.DocumentsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users/documents")
public class DocumentsController {

    @Autowired
    private final DocumentsService documentsService;

    @PostMapping("/save/{userId}")
    public ResponseEntity<String> uploadDocument(@PathVariable Long userId,
                                                 @RequestParam("file") MultipartFile file,
                                                 @RequestParam("documentType") String documentType) {
        try {
            documentsService.saveDocument(file, userId, documentType);
            return new ResponseEntity<>("Document uploaded successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error uploading document: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documents> getDocument(@PathVariable Long userId, @PathVariable Long id) {
        Optional<Documents> document = documentsService.getDocumentById(id);
        if (document.isPresent()) {
            return new ResponseEntity<>(document.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
