package com.example.demoSpring1.service;

import com.example.demoSpring1.entity.Document;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService
{
    Document uploadDocuments(MultipartFile file, Long registrationId, String documentType);



    Document getDocumentById(Long documentId);

//   Document Generatepdf();
}
