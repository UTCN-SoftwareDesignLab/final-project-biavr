package com.recruitment.company;

import com.recruitment.company.dto.CompanyDTO;
import com.recruitment.company.model.Company;
import com.recruitment.user.RoleRepository;
import com.recruitment.user.UserRepository;
import com.recruitment.user.model.ERole;
import com.recruitment.user.model.Role;
import com.recruitment.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static com.recruitment.user.model.ERole.RECRUITER;
import static com.recruitment.user.model.ERole.values;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        companyRepository.deleteAll();
    }

    @Test
    void findCompanyByUsersIsLike() {
        buildRoles();
        final User recruiter = userRepository.save(User.builder()
                .username("recruiter")
                .email("recruiter@users.com")
                .password("stronk")
                .roles(Set.of(roleRepository.findByName(RECRUITER).get()))
                .build());
        User user = userRepository.findByUsername(recruiter.getUsername()).get();
        Company company = Company.builder()
                .name("Company")
                .description("description")
                .users(Set.of(user)).build();

        companyRepository.save(company);

        Company result = companyRepository.findCompanyByUsersIsLike(recruiter);

        assertEquals(company.getName(),result.getName());
        assertEquals(recruiter.getUsername(),result.getUsers().iterator().next().getUsername());
    }

    private void buildRoles() {
        for (ERole value : values()) {
            roleRepository.save(
                    Role.builder()
                            .name(value)
                            .build()
            );
        }
    }
}