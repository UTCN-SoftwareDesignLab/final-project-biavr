package com.recruitment;

import com.recruitment.company.CompanyRepository;
import com.recruitment.company.CompanyService;
import com.recruitment.company.dto.CompanyDTO;
import com.recruitment.item.ItemRepository;
import com.recruitment.job.JobRepository;
import com.recruitment.job.JobService;
import com.recruitment.job.dto.JobDTO;
import com.recruitment.job.mapper.JobMapper;
import com.recruitment.security.AuthService;
import com.recruitment.security.dto.SignupRequest;
import com.recruitment.user.RoleRepository;
import com.recruitment.user.UserRepository;
import com.recruitment.user.model.ERole;
import com.recruitment.user.model.Role;
import com.recruitment.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final ItemRepository itemRepository;

    private final CompanyService companyService;

    private final CompanyRepository companyRepository;

    private final JobService jobService;

    private final JobRepository jobRepository;

    private final JobMapper jobMapper;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            itemRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            jobRepository.deleteAll();
            companyRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("bianca@email.com")
                    .username("biancaA")
                    .password("WooHoo1!")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("bianca1@email.com")
                    .username("biancaRamazon")
                    .password("WooHoo1!")
                    .roles(Set.of("RECRUITER"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("bianca4@email.com")
                    .username("biancaRebay")
                    .password("WooHoo1!")
                    .roles(Set.of("RECRUITER"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("bianca5@email.com")
                    .username("biancaRgoogle")
                    .password("WooHoo1!")
                    .roles(Set.of("RECRUITER"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("bianca6@email.com")
                    .username("biancaRutcn")
                    .password("WooHoo1!")
                    .roles(Set.of("RECRUITER"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("bianca7@email.com")
                    .username("biancaRmicrosoft")
                    .password("WooHoo1!")
                    .roles(Set.of("RECRUITER"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("bianca2@email.com")
                    .username("biancaS1")
                    .password("WooHoo1!")
                    .roles(Set.of("SEEKER"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("bianca3@email.com")
                    .username("biancaS2")
                    .password("WooHoo1!")
                    .roles(Set.of("SEEKER"))
                    .build());

            JobDTO seng = jobService.create(JobDTO.builder()
                    .name("Software engineer")
                    .description("Description of software engineer")
                    .domain("IT").build());
            JobDTO sale = jobService.create(JobDTO.builder()
                    .name("Sales assistant")
                    .description("Description of sales assistant")
                    .domain("Sales").build());
            JobDTO hr =jobService.create(JobDTO.builder()
                    .name("HR specialist")
                    .description("Description of HR specialist")
                    .domain("HR").build());
            JobDTO lecturer =jobService.create(JobDTO.builder()
                    .name("Lecturer")
                    .description("Description of lecturer")
                    .domain("Teaching").build());
            JobDTO pr =jobService.create(JobDTO.builder()
                    .name("Public Relations Intern")
                    .description("Description of PR")
                    .domain("PR").build());

            companyService.create(CompanyDTO.builder()
                    .name("Amazon")
                    .description("This is the description of Amazon.")
                    .jobs(Set.of(jobMapper.fromDTO(seng)))
                    .users(Set.of(userRepository.findByUsername("biancaRamazon").get()))
                    .build());
            companyService.create(CompanyDTO.builder()
                    .name("Ebay")
                    .description("This is the description of Ebay.")
                    .jobs(Set.of(jobMapper.fromDTO(sale)))
                    .users(Set.of(userRepository.findByUsername("biancaRebay").get()))
                    .build());
            companyService.create(CompanyDTO.builder()
                    .name("Google")
                    .description("This is the description of Google.")
                    .jobs(Set.of(jobMapper.fromDTO(hr)))
                    .users(Set.of(userRepository.findByUsername("biancaRgoogle").get()))
                    .build());
            companyService.create(CompanyDTO.builder()
                    .name("Technical University of Cluj-Napoca")
                    .description("This is the description of TUCN.")
                    .jobs(Set.of(jobMapper.fromDTO(lecturer)))
                    .users(Set.of(userRepository.findByUsername("biancaRutcn").get()))
                    .build());
            companyService.create(CompanyDTO.builder()
                    .name("Microsoft")
                    .description("This is the description of Microsoft.")
                    .jobs(Set.of(jobMapper.fromDTO(pr)))
                    .users(Set.of(userRepository.findByUsername("biancaRmicrosoft").get()))
                    .build());
        }
    }
}
