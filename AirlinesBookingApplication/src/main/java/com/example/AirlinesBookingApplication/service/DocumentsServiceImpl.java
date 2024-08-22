package com.example.AirlinesBookingApplication.service;

import com.example.AirlinesBookingApplication.entity.Documents;
import com.example.AirlinesBookingApplication.entity.User;
//import com.example.AirlinesBookingApplication.repository.DocumentsRepository;
import com.example.AirlinesBookingApplication.repository.DocumentsRepsoitory;
import com.example.AirlinesBookingApplication.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import static java.time.LocalTime.now;

@AllArgsConstructor
@Service
public class DocumentsServiceImpl implements DocumentsService {

     @Autowired
    private final DocumentsRepsoitory documentsRepository;
     @Autowired
    private final UserRepository userRepository;


    @Override
    public void saveDocument(MultipartFile file, Long userId, String documentType) throws Exception {
        // Retrieve the user entity from the repository
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));

        // Create a new document entity
        Documents document = new Documents();
        document.setFilename(file.getOriginalFilename());
        document.setFiletype(getFileExtension(file.getOriginalFilename()));
        document.setData(file.getBytes());
        document.setDocumentType(documentType);
        document.setUploadedTime(LocalDateTime.now()); // Set current time
        document.setUser(user);

        // Save the document entity to the repository
        documentsRepository.save(document);
    }

    @Override
    public Optional<Documents> getDocumentById(Long id) {
        return documentsRepository.findById(id);
    }

    // Utility method to get the file extension from the filename
    private String getFileExtension(String filename) {
        int lastIndex = filename.lastIndexOf('.');
        if (lastIndex == -1) {
            return ""; // No extension
        }
        return filename.substring(lastIndex + 1);
    }
}
