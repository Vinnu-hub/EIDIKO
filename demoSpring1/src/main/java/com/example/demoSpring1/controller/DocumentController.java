package com.example.demoSpring1.controller;

import com.example.demoSpring1.entity.Document;
import com.example.demoSpring1.service.DocumentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.Base64;

@RestController
@RequestMapping("/api/documents")
public class DocumentController
{

    @Autowired
    private DocumentService documentService;

    @PostMapping("/upload/{registrationId}")
    public ResponseEntity<String> uploadDocument(@PathVariable("registrationId") Long registrationId,
                                                 @RequestParam("file") MultipartFile file,
                                                 @RequestParam("documentType") String documentType)
    {
        try {
            documentService.uploadDocuments(file, registrationId, documentType);
            return new ResponseEntity<>("Document uploaded and registration status updated", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error uploading document: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/download/{documentId}")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable("documentId") Long documentId) {
        // Fetch the document from the service
        Document document = documentService.getDocumentById(documentId);

        // Decode Base64 encoded file data
//        byte[] fileData = Base64.getDecoder().decode(document.getData());

        // Return the response entity with file data, content disposition, and headers
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)  // Set content type as application/octet-stream
                .body(document.getData().getBytes());  // Return the decoded file data
    }

}
//
//
//@GetMapping("/downloadfile/{id}")
//public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
//    AirlineFileHandling airlineFileHandling = airlinesService.getFile(id);
//    return ResponseEntity.ok()
//            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + airlineFileHandling.getFilename() + "\"")
//            .body(airlineFileHandling.getData());






































