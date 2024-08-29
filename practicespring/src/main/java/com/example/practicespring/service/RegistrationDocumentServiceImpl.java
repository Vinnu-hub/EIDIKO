package com.example.practicespring.service;

import com.example.practicespring.entity.RegisterDocuments;
import com.example.practicespring.entity.Registration;
import com.example.practicespring.repository.RegistrationDocumentsRepository;
import com.example.practicespring.repository.RegistrationRepository;
import com.example.practicespring.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RegistrationDocumentServiceImpl implements RegistrationDocumentService
{

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private RegistrationDocumentsRepository registrationDocumentsRepository;

    @Override
    public void uploadDocument(MultipartFile file, Long registrationId, String documentType)
    {
        try {
            if (file == null || file.isEmpty()) {
                throw new IllegalArgumentException("File must not be null or empty");
            }

            Registration registration = registrationRepository.findById(registrationId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            RegisterDocuments registerDocuments = new RegisterDocuments();
            registerDocuments.setFileName(file.getOriginalFilename());
            registerDocuments.setFileExtension(file.getContentType());
            registerDocuments.setDocumentType(documentType);
            registerDocuments.setUploadedTime(LocalDateTime.now());
            registerDocuments.setRegistration(registration);

            // Convert file to Base64 string
            String base64Data = Base64Util.encode(file.getBytes());
            registerDocuments.setData(base64Data);

            registrationDocumentsRepository.save(registerDocuments);

            // Update the status of the registration
            registration.setStatus("approved");
            registrationRepository.save(registration);
        } catch (IOException e) {
            throw new RuntimeException("Error while processing file", e);
        }
    }

    @Override
    public Optional<RegisterDocuments> getDocumentById(Long id)
    {
        return registrationDocumentsRepository.findById(id).map(doc -> {
            // Decode Base64 data before returning
            byte[] decodedData = Base64Util.decode(doc.getData());
            // Convert byte[] to String if needed for response or set directly
            doc.setData(new String(decodedData)); // Adjust this line if needed
            return doc;
        });
    }
}
