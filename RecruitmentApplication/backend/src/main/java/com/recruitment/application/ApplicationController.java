package com.recruitment.application;

import com.recruitment.application.dto.ApplicationDTO;
import com.recruitment.application.mapper.ApplicationMapper;
import com.recruitment.company.dto.CompanyDTO;
import com.recruitment.email.EmailService;
import com.recruitment.upload.FileDB;
import com.recruitment.upload.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

import static com.recruitment.UrlMapping.APPLICATIONS;
import static com.recruitment.UrlMapping.ENTITY;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService service;
    private final ApplicationMapper mapper;
    private final EmailService emailService;
    private final FileStorageService fileStorageService;

    @PostMapping(APPLICATIONS)
    public ApplicationDTO create(@RequestBody ApplicationDTO app) {
        return service.create(app);
    }

    @PatchMapping(APPLICATIONS + ENTITY)
    public ApplicationDTO edit(@PathVariable Long id, @RequestBody ApplicationDTO app){
        //here we also want to send an email with "YOUR APPLICATION HAS BEEN REVIEWED" + the review on the applicatoin
        return service.edit(id,app);
    }

    @DeleteMapping(APPLICATIONS + ENTITY)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping(APPLICATIONS + "/list" + ENTITY)
    public List<ApplicationDTO> allApplications(@PathVariable Long id){
        List<ApplicationDTO> apps = service.findAllForCompany(id);
        return apps;
    }
}
