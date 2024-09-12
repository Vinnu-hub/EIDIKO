package com.example.demoSpring1.service;

import com.example.demoSpring1.entity.Document;
import com.example.demoSpring1.entity.Registration;
import com.example.demoSpring1.exception.UseridNotFound;
import com.example.demoSpring1.repository.DocumentsRepository;
import com.example.demoSpring1.repository.registrationRepository;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService
{

    @Autowired
    private registrationRepository registrationRepo;

    @Autowired
    private DocumentsRepository documentsRepository;

    @Override
    public Document uploadDocuments(MultipartFile file, Long registrationId, String documentType)
    {
        // Fetch the registration details based on the provided ID
        Registration registration = registrationRepo.findById(registrationId)
                .orElseThrow(() -> new UseridNotFound("User id is not found: " + registrationId));

        try {
            // Create a new Document entity and set its fields
            Document document = new Document();
            document.setFileName(file.getOriginalFilename());
            document.setDocumentType(documentType);
            document.setLocalDateTime(LocalDateTime.now());

            // Encode the file data to Base64
            byte[] fileBytes = file.getBytes();
            String encodedFileData = Base64.getEncoder().encodeToString(fileBytes);
            document.setData(encodedFileData);  // Store the Base64-encoded file data

            // Link the document with the associated registration
            document.setRegistration(registration);

            // Update the registration status to 'completed' (or 'uploaded')
            registration.setStatus("completed");

            // Save the registration with updated status
            registrationRepo.save(registration);

            // Save the document entity with all details
            return documentsRepository.save(document);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload document", e);
        }
    }




    @Override
    public Document getDocumentById(Long documentId) {
        return documentsRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
    }

//    @Override
//    public Document Generatepdf()
//    {
//
//   List<Registration> registration=registrationRepo.findAll();
//
//    Document document=new Document();
//        ByteArrayOutputStream out=new ByteArrayOutputStream();
//        try
//        {
//            PdfWriter.getInstance(document,out);
//            document.open();
//
//            // to dd paragpt h
//            Paragraph title=new Paragraph("Registration User_details");
//            title.setAlignment(Element.ALIGN_CENTER);
//            document.add(title);
//            document.add(new Paragraph(" "));
//
//
//
//
//        } catch (DocumentException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

}
