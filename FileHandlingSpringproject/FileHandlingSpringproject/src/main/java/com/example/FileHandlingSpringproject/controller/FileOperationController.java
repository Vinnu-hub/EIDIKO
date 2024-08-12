package com.example.FileHandlingSpringproject.controller;

import com.example.FileHandlingSpringproject.dto.FileOperationDto;
import com.example.FileHandlingSpringproject.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/files")
public class FileOperationController {

    @Autowired
    private FileService fileService;

    // Create or Update File
    @PostMapping("/add")
    public ResponseEntity<FileOperationDto> saveFile(@RequestBody FileOperationDto fileOperationDto) {
        FileOperationDto savedFile = fileService.saveFile(fileOperationDto);
        return new ResponseEntity<>(savedFile, HttpStatus.CREATED);
    }

    // Get File by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<FileOperationDto> getFileById(@PathVariable Integer id) {
        FileOperationDto fileOperationDto = fileService.getFileById(id);
        if (fileOperationDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fileOperationDto, HttpStatus.OK);
    }

    // Get All Files
    @GetMapping("/all")
    public ResponseEntity<List<FileOperationDto>> getAllFiles() {
        List<FileOperationDto> fileOperations = fileService.getAllFiles();
        return new ResponseEntity<>(fileOperations, HttpStatus.OK);
    }

    // Update File
    @PutMapping("/update/{id}")
    public ResponseEntity<FileOperationDto> updateFile(
            @PathVariable Integer id, @RequestBody FileOperationDto fileOperationDto) {
        FileOperationDto updatedFile = fileService.updateFile(id, fileOperationDto);
        if (updatedFile == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedFile, HttpStatus.OK);
    }

    // Delete File by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Integer id) {
        fileService.deleteFile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
