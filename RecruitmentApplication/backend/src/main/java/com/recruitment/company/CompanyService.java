package com.recruitment.company;

import com.recruitment.company.dto.CompanyDTO;
import com.recruitment.company.mapper.CompanyMapper;
import com.recruitment.company.model.Company;
import com.recruitment.job.dto.JobDTO;
import com.recruitment.job.mapper.JobMapper;
import com.recruitment.user.UserRepository;
import com.recruitment.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final UserRepository userRepository;
    private final JobMapper jobMapper;

    private Company findById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found: " + id));
    }

    public List<CompanyDTO> findAll() {
        return companyRepository.findAll().stream()
                .map(companyMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CompanyDTO create(CompanyDTO item) {
        return companyMapper.toDTO(companyRepository.save(
                companyMapper.fromDTO(item)
        ));
    }

    public CompanyDTO edit(Long id, CompanyDTO company) {
        Company actCompany = findById(id);
        actCompany.setName(company.getName());
        actCompany.setDescription(company.getDescription());
        actCompany.setJobs(company.getJobs().stream().collect(Collectors.toSet()));
        return companyMapper.toDTO(
                companyRepository.save(actCompany)
        );
    }

    public void delete(Long id){
        companyRepository.deleteById(id);
    }

    public CompanyDTO get(Long id){
        return companyMapper.toDTO(findById(id));
    }

    public CompanyDTO getByUserId(Long id){
        User user = userRepository.findById(id).get();
        return companyMapper.toDTO(companyRepository.findCompanyByUsersIsLike(user));
    }

    public List<JobDTO> findJobsByCompanyId(Long id){
        Company company = findById(id);
        List<JobDTO> jobs = new ArrayList<>();
        jobs.addAll(company.getJobs().stream().map(jobMapper::toDTO).collect(Collectors.toList()));
        return jobs;
    }

}
