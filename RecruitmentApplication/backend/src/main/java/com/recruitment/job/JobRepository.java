package com.recruitment.job;

import com.recruitment.job.dto.JobDTO;
import com.recruitment.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    JobDTO findByName(String name);
}
