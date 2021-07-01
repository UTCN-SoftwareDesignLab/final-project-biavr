package com.recruitment.company;

import com.recruitment.company.dto.CompanyDTO;
import com.recruitment.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.hibernate.jpa.internal.ExceptionMapperLegacyJpaImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.recruitment.UrlMapping.COMPANIES;
import static com.recruitment.UrlMapping.ENTITY;


@RestController
@RequestMapping(COMPANIES)
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService service;

    @GetMapping
    public List<CompanyDTO> allCompanies(){
        return service.findAll();
    }

    @PostMapping
    public CompanyDTO create(@RequestBody CompanyDTO companyDTO){
        return service.create(companyDTO);
    }

    @PatchMapping(ENTITY)
    public CompanyDTO edit(@PathVariable Long id, @RequestBody CompanyDTO companyDTO){
        return service.edit(id,companyDTO);
    }

    @GetMapping("/jobs/recruiter" + ENTITY)
    public CompanyDTO getByUserId(@PathVariable Long id){
        CompanyDTO companyDTO = service.getByUserId(id);
        return companyDTO;
    }

    @GetMapping("/jobs" + ENTITY)
    public CompanyDTO getCompanyById(@PathVariable Long id){
        CompanyDTO companyDTO = service.get(id);
        return companyDTO;
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
