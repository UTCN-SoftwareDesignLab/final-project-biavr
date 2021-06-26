package com.recruitment.application.dto;

import com.recruitment.job.model.Job;
import com.recruitment.upload.FileDB;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String message;
    private FileDB resume;
    private String review;
    private Job job;
}
