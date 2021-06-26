package com.recruitment.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationRepositoryTest {

    @Autowired
    private ApplicationRepository repository;


    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void findAllByJobLike() {

    }
}