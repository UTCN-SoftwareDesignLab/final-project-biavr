package com.recruitment.company;

import com.recruitment.BaseControllerTest;
import com.recruitment.company.mapper.CompanyMapper;
import com.recruitment.company.model.Company;
import com.recruitment.email.EmailService;
import com.recruitment.user.UserService;
import com.recruitment.user.model.Role;
import com.recruitment.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;

import static com.recruitment.UrlMapping.JOBS;
import static com.recruitment.user.model.ERole.RECRUITER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CompanyControllerTest extends BaseControllerTest {

    @InjectMocks
    private CompanyController controller;

    @Mock
    private CompanyService service;

    @Mock
    private CompanyMapper mapper;

    @Mock
    private EmailService emailService;

    @BeforeEach
    void setup() {
        super.setUp();
        controller = new CompanyController(service, emailService);
        mockMvc= MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    void get() throws Exception {
        User user = User.builder()
                .id(5L)
                .email("userR@gmail.com")
                .password("blvasdFASf%53")
                .roles(Set.of(Role.builder().name(RECRUITER).build())).build();

        Company company = Company.builder()
                .name("Company")
                .description("description")
                .users(Set.of(user)).build();

        when(service.getByUserId(user.getId())).thenReturn(mapper.toDTO(company));

        ResultActions response = performGetWithPathVariable(JOBS,user.getId());

        response.andExpect(status().isOk())
                .andExpect(jsonContentToBe(company));
    }
}