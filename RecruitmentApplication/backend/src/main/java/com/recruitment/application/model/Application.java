package com.recruitment.application.model;

import com.recruitment.job.model.Job;
import com.recruitment.upload.FileDB;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512, nullable = false)
    private String firstName;

    @Column(length = 512, nullable = false)
    private String lastName;

    @Email
    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 12)
    private String phoneNumber;

    @Column(nullable = true, length = 256)
    private String address;

    @Column(nullable = true, length = 1024)
    private String message;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "resume_id", referencedColumnName = "id")
    @Column
    private String resume;

    @Column
    private String review;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "applied_job",
            joinColumns = @JoinColumn(name = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id"))
    private Job job;
}
