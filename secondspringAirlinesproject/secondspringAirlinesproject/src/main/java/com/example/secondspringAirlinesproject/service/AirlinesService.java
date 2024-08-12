package com.example.secondspringAirlinesproject.service;

import com.example.secondspringAirlinesproject.Dto.AirlinesDto;

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
}
