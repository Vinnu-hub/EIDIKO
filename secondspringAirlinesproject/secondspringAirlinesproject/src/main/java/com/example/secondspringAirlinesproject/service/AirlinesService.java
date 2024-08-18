package com.example.secondspringAirlinesproject.service;

import com.example.secondspringAirlinesproject.Dto.AirlinesDto;
import com.example.secondspringAirlinesproject.entity.AirlineFileHandling;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

public interface AirlinesService {

    // Create or update an airline
    AirlinesDto saveOrUpdateAirline(AirlinesDto airlineDto);

    // Fetch all airlines
    List<AirlinesDto> getAllAirlines();

    // Fetch airline by ID
    Optional<AirlinesDto> getAirlineById(Long id);

    // Delete airline by ID
    void deleteAirlineById(Long id);

    // Delete airline entity
    void deleteAirline(AirlinesDto airlineDto);


//methods for the file operation

    //void aveFile(MultipartFile file, Long recordId) throws Exception;
    void saveFile(MultipartFile file, Long id) throws Exception;

    AirlineFileHandling getFile(Long id);

    ByteArrayInputStream generatePdf();

    ByteArrayInputStream generateExcel();


}
