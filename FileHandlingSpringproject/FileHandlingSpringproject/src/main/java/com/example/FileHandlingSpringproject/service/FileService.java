package com.example.FileHandlingSpringproject.service;

import com.example.FileHandlingSpringproject.dto.FileOperationDto;
import com.example.FileHandlingSpringproject.entity.FileOperation;
import com.example.FileHandlingSpringproject.mapper.FileOperationMapper;
import com.example.FileHandlingSpringproject.repository.fileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private fileRepository fileRepository;

    // Create or Update File
    public FileOperationDto saveFile(FileOperationDto fileOperationDto) {
        FileOperation fileOperation = FileOperationMapper.toEntity(fileOperationDto);
        FileOperation savedFileOperation = fileRepository.save(fileOperation);
        return FileOperationMapper.toDto(savedFileOperation);
    }

    // Read File by ID
    public FileOperationDto getFileById(Integer id) {
        Optional<FileOperation> fileOperationOptional = fileRepository.findById(id);
        return fileOperationOptional.map(FileOperationMapper::toDto).orElse(null);
    }

    // Read All Files
    public List<FileOperationDto> getAllFiles() {
        List<FileOperation> fileOperations = fileRepository.findAll();
        return fileOperations.stream()
                .map(FileOperationMapper::toDto)
                .toList();
    }

    // Update File
    public FileOperationDto updateFile(Integer id, FileOperationDto fileOperationDto) {
        if (!fileRepository.existsById(id)) {
            return null; // Or throw an exception, depending on your error handling strategy
        }

        FileOperation fileOperation = FileOperationMapper.toEntity(fileOperationDto);
        fileOperation.setId(id); // Ensure the ID is set for the update
        FileOperation updatedFileOperation = fileRepository.save(fileOperation);
        return FileOperationMapper.toDto(updatedFileOperation);
    }

    // Delete File by ID
    public void deleteFile(Integer id) {
        if (fileRepository.existsById(id)) {
            fileRepository.deleteById(id);
        }
    }
}
