package com.recruitment.job;

import com.recruitment.job.dto.JobDTO;
import com.recruitment.job.mapper.JobMapper;
import com.recruitment.job.model.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobMapper mapper;
    private final JobRepository repository;

    @Autowired
    private final SimpMessagingTemplate template;

    private Job findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job not found: " + id));
    }

    public List<JobDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public JobDTO create(JobDTO job) {
        this.template.convertAndSend("/topic/socket/companies","New job available: "+job.getName());
        return mapper.toDTO(repository.save(
                mapper.fromDTO(job)
        ));
    }

    public JobDTO edit(Long id, JobDTO job) {
        Job actJob = findById(id);
        actJob.setDescription(job.getDescription());
        actJob.setDomain(job.getDomain());
        actJob.setName(job.getName());
        return mapper.toDTO(
                repository.save(actJob)
        );
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public JobDTO get(Long id){
        return mapper.toDTO(findById(id));
    }
}
