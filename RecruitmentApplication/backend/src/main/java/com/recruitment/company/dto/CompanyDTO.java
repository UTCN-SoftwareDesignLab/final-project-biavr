package com.recruitment.company.dto;

import com.recruitment.job.model.Job;
import com.recruitment.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
    private Long id;
    private String name;
    private String description;
    private Set <Job> jobs;
    private Set <User> users;
}
