package com.klu.talent_job_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class JobControllerTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobController jobController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createJobTest() {

        // Create test job
        Job job = new Job();

        job.setTitle("Java Developer");
        job.setDescription("Java Backend Developer");
        job.setCompany("KL Solutions");
        job.setLocation("Vijayawada");
        job.setSalary("6 LPA");
        job.setRecruiterId(1L);

        // Mock repository save()
        when(jobRepository.save(any(Job.class)))
                .thenReturn(job);

        // Call controller method
        ResponseEntity<Job> response =
                jobController.createJob(job);

        // Check response
        assertEquals(200, response.getStatusCode().value());

        // Check response body
        assertNotNull(response.getBody());

        assertEquals(
                "Java Developer",
                response.getBody().getTitle()
        );

        assertEquals(
                "KL Solutions",
                response.getBody().getCompany()
        );

        // Verify repository was called
        verify(jobRepository).save(job);
    }
}