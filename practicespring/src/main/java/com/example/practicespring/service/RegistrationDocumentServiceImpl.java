package com.example.practicespring.service;

import com.example.practicespring.entity.RegisterDocuments;
import com.example.practicespring.entity.Registration;
import com.example.practicespring.repository.RegistrationDocumentsRepository;
import com.example.practicespring.repository.RegistrationRepository;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Service
public class RegistrationDocumentServiceImpl implements RegistrationDocumentService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private RegistrationDocumentsRepository registrationDocumentsRepository;

    private final Tesseract tesseract;

    public RegistrationDocumentServiceImpl()
    {
        tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Program Files (x86)\\Tesseract-OCR\\tessdata"); // Set the correct path for tessdata
        tesseract.setLanguage("eng"); // Set language to English
    }

    @Override
    public String extractTextFromImage(MultipartFile file) {
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());

            // Create a temporary file for conversion
            File tempFile = Files.createTempFile("tempImage", ".png").toFile();

            // Write the buffered image as PNG
            ImageIO.write(bufferedImage, "png", tempFile);

            // Perform OCR on the converted file
            String extractedText = tesseract.doOCR(tempFile);

            // Clean up temporary file
            Files.delete(tempFile.toPath());

            return extractedText;

        } catch (TesseractException | IOException e)
        {
            throw new RuntimeException("Error extracting text from image", e);
        }
    }

    @Override
    public String extractTextFromPDF(MultipartFile file) {
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        } catch (IOException e) {
            throw new RuntimeException("Error extracting text from PDF", e);
        }
    }

    @Override
    public String uploadDocument(MultipartFile file, Long registrationId) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File must not be null or empty");
        }

        // Fetch registration entity by ID
        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new RuntimeException("Registration not found"));

        // Create new RegisterDocuments entity
        RegisterDocuments registerDocuments = new RegisterDocuments();
        registerDocuments.setFileName(file.getOriginalFilename());
        registerDocuments.setFileExtension(file.getContentType());
        registerDocuments.setUploadedTime(LocalDateTime.now());
        registerDocuments.setRegistration(registration);

        // Determine file type and extract text accordingly
        String extractedText = "";
        String contentType = file.getContentType();
        if (contentType != null) {
            if (contentType.startsWith("image/")) {
                extractedText = extractTextFromImage(file);
                registerDocuments.setDocumentType("image");
            } else if (contentType.equals("application/pdf")) {
                extractedText = extractTextFromPDF(file);
                registerDocuments.setDocumentType("pdf");
            } else {
                throw new IllegalArgumentException("Unsupported file type: " + contentType);
            }
        }

        // Save extracted text and document details
        registerDocuments.setTextData(extractedText);
        registrationDocumentsRepository.save(registerDocuments);

        // Optionally update registration status
        registration.setStatus("Approved");
        registrationRepository.save(registration);

        return extractedText;  // Return extracted text
    }
}
