package com.example.secondspringAirlinesproject.controller;

import com.example.secondspringAirlinesproject.Dto.AirlinesDto;
import com.example.secondspringAirlinesproject.entity.AirlineFileHandling;
import com.example.secondspringAirlinesproject.service.AirlinesService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/airlines")
@AllArgsConstructor
public class AirlinesController {

    private final AirlinesService airlinesService;

    // Create or update an airline
    @PostMapping("/save")  // Use a descriptive path like "save" for clarity
    public ResponseEntity<AirlinesDto> createOrUpdateAirline(@RequestBody AirlinesDto airlineDto) {//Binds the request body to an AirlinesDto object
        AirlinesDto savedAirlineDto = airlinesService.saveOrUpdateAirline(airlineDto);
        return ResponseEntity.ok(savedAirlineDto);
    }

    // Get all airlines
    @GetMapping("/all")  // Use "/all" or "/list" to signify fetching all records
    public ResponseEntity<List<AirlinesDto>> getAllAirlines() {
        List<AirlinesDto> airlinesDtos = airlinesService.getAllAirlines();
        return ResponseEntity.ok(airlinesDtos); // Returns an HTTP 200 OK response with a list of all airlines.
    }

    // Get airline by ID
    @GetMapping("/{id}")
    public ResponseEntity<AirlinesDto> getAirlineById(@PathVariable Long id) {
        Optional<AirlinesDto> airlineDto = airlinesService.getAirlineById(id);
        return airlineDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete airline by ID
    @DeleteMapping("/delete/{id}")  // Use "/delete/{id}" for deletion by ID
    public ResponseEntity<Void> deleteAirlineById(@PathVariable Long id) {//Binds the path variable id to a Long parameter.
        airlinesService.deleteAirlineById(id);
        return ResponseEntity.noContent().build();////Returns an HTTP 204 No Content response
                                              // indicating successful deletion.
    }

    // Delete airline entity
    @DeleteMapping("/delete")  // Use "/delete" for deletion based on the entity
    public ResponseEntity<Void> deleteAirline(@RequestBody AirlinesDto airlineDto) {
        airlinesService.deleteAirline(airlineDto);
        return ResponseEntity.noContent().build();//Returns an HTTP 204 No Content response
                                                // indicating successful deletion.
    }




    // foe the fileHandlling


    @PostMapping("/fileuploadfile/{airlineNumber}")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @PathVariable Long airlineNumber) throws Exception {
        // Binds the file part of the request to a MultipartFile object
        airlinesService.saveFile(file, airlineNumber);
        return ResponseEntity.ok("File uploaded successfully!");
    }

    // API for downloading a file
    @GetMapping("/downloadfile/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        AirlineFileHandling airlineFileHandling = airlinesService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + airlineFileHandling.getFilename() + "\"")
                .body(airlineFileHandling.getData());
    }

    // API for downloading a PDF containing airline data
    @GetMapping("/download_pdf")
    public ResponseEntity<InputStreamResource> downloadPdf() {
        ByteArrayInputStream pdfStream = airlinesService.generatePdf();//Calls the service method to generate a PDF containing airline data.

        HttpHeaders headers = new HttpHeaders();// Creates a new HttpHeaders object.
        headers.add("Content-Disposition", "attachment; filename=airlines.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfStream)); // Returns an HTTP 200 OK response with the PDF data and headers for downloading the file.
    }

    // API for downloading an Excel file containing airline data
    @GetMapping("/exceelsheet-downloads")
    public ResponseEntity<InputStreamResource> downloadExcel() {
        ByteArrayInputStream in = airlinesService.generateExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=airlines.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(new InputStreamResource(in));
    }
}

//@RestController: Indicates that this class is a Spring REST controller. It combines @Controller and
// @ResponseBody, meaning that methods in this class will return data directly to the client, not views.

