package com.recruitment.job.mapper;

import com.recruitment.job.dto.JobDTO;
import com.recruitment.job.model.Job;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper {
    Job fromDTO(JobDTO job);

    JobDTO toDTO(Job job);
}
