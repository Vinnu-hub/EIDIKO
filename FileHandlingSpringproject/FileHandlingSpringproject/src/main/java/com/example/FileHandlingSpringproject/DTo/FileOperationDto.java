package com.example.FileHandlingSpringproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FileOperationDto {
    private Integer id;
    private String filename;
    private String filetype;
    private String userId;
    private String uploadedAt; // Use Java naming conventions (camelCase)
}
