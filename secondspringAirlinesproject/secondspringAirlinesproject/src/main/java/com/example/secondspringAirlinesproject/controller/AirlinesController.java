package com.example.secondspringAirlinesproject.controller;

import com.example.secondspringAirlinesproject.Dto.AirlinesDto;
import com.example.secondspringAirlinesproject.service.AirlinesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/airlines")
@AllArgsConstructor
public class AirlinesController {

    private final AirlinesService airlinesService;

    // Create or update an airline
    @PostMapping("/{insert}")
    public ResponseEntity<AirlinesDto> createOrUpdateAirline(@RequestBody AirlinesDto airlineDto) {
        AirlinesDto savedAirlineDto = airlinesService.saveOrUpdateAirline(airlineDto);
        return ResponseEntity.ok(savedAirlineDto);
    }

    // Get all airlines
    @GetMapping("/{get}")
    public ResponseEntity<List<AirlinesDto>> getAllAirlines() {
        List<AirlinesDto> airlinesDtos = airlinesService.getAllAirlines();
        return ResponseEntity.ok(airlinesDtos);
    }

    // Get airline by ID
    @GetMapping("/{id}")
    public ResponseEntity<AirlinesDto> getAirlineById(@PathVariable Long id) {
        Optional<AirlinesDto> airlineDto = airlinesService.getAirlineById(id);
        return airlineDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete airline by ID
    @DeleteMapping("/{delete/id}")
    public ResponseEntity<Void> deleteAirlineById(@PathVariable Long id) {
        airlinesService.deleteAirlineById(id);
        return ResponseEntity.noContent().build();
    }

    // Delete airline entity
    @DeleteMapping("/{deletetable}")
    public ResponseEntity<Void> deleteAirline(@RequestBody AirlinesDto airlineDto) {
        airlinesService.deleteAirline(airlineDto);
        return ResponseEntity.noContent().build();
    }
}
