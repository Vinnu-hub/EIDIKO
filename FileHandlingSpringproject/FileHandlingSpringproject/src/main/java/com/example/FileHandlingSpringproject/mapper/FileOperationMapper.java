package com.example.FileHandlingSpringproject.mapper;

import com.example.FileHandlingSpringproject.dto.FileOperationDto;
import com.example.FileHandlingSpringproject.entity.FileOperation;

public class FileOperationMapper {

    // Convert FileOperation entity to FileOperationDto
    public static FileOperationDto toDto(FileOperation fileOperation) {
        if (fileOperation == null) {
            return null;
        }
        return new FileOperationDto(
                fileOperation.getId(),
                fileOperation.getFilename(),
                fileOperation.getFiletype(),
                fileOperation.getUserId(),
                fileOperation.getUploadedAt()
        );
    }

    // Convert FileOperationDto to FileOperation entity
    public static FileOperation toEntity(FileOperationDto fileOperationDto) {
        if (fileOperationDto == null) {
            return null;
        }
        return new FileOperation(
                fileOperationDto.getId(),
                fileOperationDto.getFilename(),
                fileOperationDto.getFiletype(),
                fileOperationDto.getUserId(),
                fileOperationDto.getUploadedAt()
        );
    }
}
