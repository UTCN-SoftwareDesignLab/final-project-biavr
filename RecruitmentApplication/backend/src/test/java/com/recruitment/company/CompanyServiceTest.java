package com.recruitment.company;
import com.recruitment.company.mapper.CompanyMapper;
import com.recruitment.company.model.Company;
import com.recruitment.job.mapper.JobMapper;
import com.recruitment.user.UserRepository;
import com.recruitment.user.model.Role;
import com.recruitment.user.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static com.recruitment.user.model.ERole.RECRUITER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CompanyServiceTest {

    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CompanyMapper companyMapper;

    @Mock
    private JobMapper jobMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        companyService = new CompanyService(companyRepository,companyMapper,userRepository,jobMapper);
    }

    @Test
    void getByUserId() {
        User user = User.builder()
                .id(5L)
                .email("userR@gmail.com")
                .password("blvasdFASf%53")
                .roles(Set.of(Role.builder().name(RECRUITER).build())).build();

        Company company = Company.builder()
                .name("Company")
                .description("description")
                .users(Set.of(user)).build();
        when(companyRepository.findCompanyByUsersIsLike(user)).thenReturn(company);

        Assertions.assertEquals(company.getUsers().iterator().next().getUsername(),user.getUsername());
    }
}