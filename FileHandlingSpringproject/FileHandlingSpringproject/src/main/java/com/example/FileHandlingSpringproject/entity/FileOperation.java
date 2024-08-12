package com.example.FileHandlingSpringproject.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FileOperation {

    @Id
     private Integer id;
     private String filename;
     private  String filetype;
     private String userId;
     private String UploadedAt;
}
