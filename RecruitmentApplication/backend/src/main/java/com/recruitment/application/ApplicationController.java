package com.recruitment.application;

import com.recruitment.application.dto.ApplicationDTO;
import com.recruitment.company.dto.CompanyDTO;
import com.recruitment.email.EmailService;
import com.recruitment.upload.FileDB;
import com.recruitment.upload.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.recruitment.UrlMapping.APPLICATIONS;
import static com.recruitment.UrlMapping.ENTITY;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService service;
    private final EmailService emailService;
    private final FileStorageService fileStorageService;

    @PostMapping(APPLICATIONS)
    public ApplicationDTO create(@RequestBody ApplicationDTO app){
        //emailService.sendEmail(app.getEmail());
        emailService.sendEmail("bianca.avram99@yahoo.ro");
        //FileDB cv = fileStorageService.getFile(app.getResume());
        return service.create(app);
    }

    @PatchMapping(APPLICATIONS + ENTITY)
    public ApplicationDTO edit(@PathVariable Long id, @RequestBody ApplicationDTO app){
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
