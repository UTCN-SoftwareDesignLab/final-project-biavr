package com.recruitment.application;

import com.recruitment.application.dto.ApplicationDTO;
import com.recruitment.application.model.Application;
import com.recruitment.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByJobLike(Job job);
    List<Application> findAllByJob(Job job);
}
