package com.recruitment.application;

import com.recruitment.application.dto.ApplicationDTO;
import com.recruitment.application.mapper.ApplicationMapper;
import com.recruitment.application.model.Application;
import com.recruitment.company.CompanyService;
import com.recruitment.company.dto.CompanyDTO;
import com.recruitment.job.model.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository repository;
    private final ApplicationMapper mapper;
    private final CompanyService companyService;

    private Application findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Application not found"));
    }

    public List<ApplicationDTO> findAllForCompany(Long id){
        CompanyDTO company = companyService.get(id);
        Set<Job> jobs = company.getJobs();
        //List<Job> jobs = company.getJobs();
        List<ApplicationDTO> apps = new ArrayList<>();
        for (Job job:jobs) {
//            System.out.print("Job: ");
//            System.out.println(job);
//            List<ApplicationDTO> foundApplications = repository.findAllByJobLike(job)
//                    .stream().map(mapper::toDTO).collect(Collectors.toList());
            List<ApplicationDTO> foundApplications = repository.findAllByJob(job);
            apps.addAll(foundApplications);
        }
        return apps;
    }

    public ApplicationDTO create(ApplicationDTO application){
        return mapper.toDTO(repository.save(mapper.fromDTO(application)));
    }

    public ApplicationDTO edit(Long id, ApplicationDTO application){
        Application actApp = findById(id);
        actApp.setMessage(application.getMessage());
        actApp.setResume(application.getResume());
        actApp.setEmail(application.getEmail());
        actApp.setAddress(application.getAddress());
        actApp.setFirstName(application.getFirstName());
        actApp.setJob(application.getJob());
        actApp.setLastName(application.getLastName());
        actApp.setReview(application.getReview());
        actApp.setPhoneNumber(application.getPhoneNumber());
        return mapper.toDTO(repository.save(actApp));
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public ApplicationDTO get(Long id){
        return mapper.toDTO(findById(id));
    }
}
