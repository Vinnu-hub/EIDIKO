package com.example.secondspringAirlinesproject.service;

import com.example.secondspringAirlinesproject.Exception.AirlineNotFoundException;
import com.example.secondspringAirlinesproject.Dto.AirlinesDto;
import com.example.secondspringAirlinesproject.entity.Airlines;
//import com.example.secondspringAirlinesproject.exception.ResourceNotFoundException;
import com.example.secondspringAirlinesproject.mapper.AirlinesMapper;
//import com.example.secondspringAirlinesproject.repository.AirlinesRepository;
import com.example.secondspringAirlinesproject.repository.Airlinesrepository;
import com.example.secondspringAirlinesproject.service.AirlinesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AirlinesServiceImpl implements AirlinesService {

    private final Airlinesrepository airlinesRepository;

    @Override
    public AirlinesDto saveOrUpdateAirline(AirlinesDto airlineDto) {
        // Convert DTO to entity
        Airlines airline = AirlinesMapper.mapToAirlines(airlineDto);
        // Save or update entity
        Airlines savedAirline = airlinesRepository.save(airline);
        // Convert entity back to DTO
        return AirlinesMapper.mapToAirlinesDto(savedAirline);
    }

    @Override
    public List<AirlinesDto> getAllAirlines() {
        List<Airlines> airlines = airlinesRepository.findAll();
        return airlines.stream().map(AirlinesMapper::mapToAirlinesDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AirlinesDto> getAirlineById(Long id) {
        Airlines airline = airlinesRepository.findById(id)
                .orElseThrow(() -> new AirlineNotFoundException(
                        "Airline not found with id: " + id));
        return Optional.of(AirlinesMapper.mapToAirlinesDto(airline));
    }

    @Override
    public void deleteAirlineById(Long id) {
        if (!airlinesRepository.existsById(id)) {
            throw new AirlineNotFoundException ("Airline not found with id: " + id);
        }
        airlinesRepository.deleteById(id);
    }

    @Override
    public void deleteAirline(AirlinesDto airlineDto) {
        Airlines airline = AirlinesMapper.mapToAirlines(airlineDto);
        if (!airlinesRepository.existsById(airline.getAirlineNumber())) {
            throw new AirlineNotFoundException ("Airline not found with id: " + airline.getAirlineNumber());
        }
        airlinesRepository.delete(airline);
    }
}
