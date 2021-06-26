package com.recruitment.company.model;

import com.recruitment.job.model.Job;
import com.recruitment.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=100)
    private String name;

    @Column(length = 2048)
    private String description;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "company_jobs",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id"))
    @Builder.Default
    private Set<Job> jobs = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "company_users",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @Builder.Default
    @Column
    private Set<User> users = new HashSet<>();
}
