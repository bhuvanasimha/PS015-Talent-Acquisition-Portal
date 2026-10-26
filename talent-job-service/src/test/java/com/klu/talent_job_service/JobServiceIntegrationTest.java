package com.klu.talent_job_service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JobServiceIntegrationTest {

    @Autowired
    private JobRepository jobRepository;

    @Test
    void applicationContextLoads() {

        assertNotNull(jobRepository);
    }
}