package com.recruitment.job;

import com.recruitment.job.dto.JobDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.recruitment.UrlMapping.*;

@RestController
@RequestMapping(JOBS)
@RequiredArgsConstructor
public class JobController {
    private final JobService service;

    @Autowired
    private SimpMessagingTemplate template;

    @GetMapping
    public List<JobDTO> allJobs(){
        return service.findAll();
    }

    @PostMapping
    public JobDTO create(@RequestBody JobDTO job){
        this.template.convertAndSend(JOB_ADDITION, "New jobs available");
        return service.create(job);
    }

    @PatchMapping(ENTITY)
    public JobDTO edit(@PathVariable Long id, @RequestBody JobDTO job){
        return service.edit(id,job);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
