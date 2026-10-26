package com.klu.talent_application_service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final JobApplicationRepository applicationRepository;

    private final JobClient jobClient;

    private final ProfileClient profileClient;


    public ApplicationController(
            JobApplicationRepository applicationRepository,
            JobClient jobClient,
            ProfileClient profileClient) {

        this.applicationRepository = applicationRepository;
        this.jobClient = jobClient;
        this.profileClient = profileClient;
    }


    // Apply for a job
    @PostMapping
    public ResponseEntity<?> applyForJob(
            @RequestBody JobApplication application) {

        try {

            // Check whether job exists
            JobResponse job =
                    jobClient.getJobById(application.getJobId());

            if (job == null) {

                return ResponseEntity
                        .badRequest()
                        .body("Job does not exist");
            }


            // Check whether applicant profile exists
            ProfileResponse profile =
                    profileClient.getProfileByUserId(
                            application.getApplicantId()
                    );

            if (profile == null) {

                return ResponseEntity
                        .badRequest()
                        .body("Applicant profile does not exist");
            }


            // Set application information
            application.setStatus("APPLIED");

            application.setAppliedDate(
                    LocalDate.now().toString()
            );


            // Save application
            JobApplication savedApplication =
                    applicationRepository.save(application);


            return ResponseEntity.ok(savedApplication);

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(
                        "Unable to create application: "
                        + e.getMessage()
                    );
        }
    }


    // Get all applications
    @GetMapping
    public ResponseEntity<List<JobApplication>> getAllApplications() {

        return ResponseEntity.ok(
                applicationRepository.findAll()
        );
    }


    // Get application by ID
    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getApplicationById(
            @PathVariable Long id) {

        return applicationRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(
                    ResponseEntity.notFound().build()
                );
    }


    // Get applications for a particular job
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<JobApplication>> getApplicationsByJob(
            @PathVariable Long jobId) {

        return ResponseEntity.ok(
                applicationRepository.findByJobId(jobId)
        );
    }


    // Get applications of a particular applicant
    @GetMapping("/applicant/{applicantId}")
    public ResponseEntity<List<JobApplication>> getApplicationsByApplicant(
            @PathVariable Long applicantId) {

        return ResponseEntity.ok(
                applicationRepository.findByApplicantId(
                        applicantId
                )
        );
    }


    // Update application status
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return applicationRepository
                .findById(id)
                .map(application -> {

                    application.setStatus(status);

                    JobApplication updated =
                            applicationRepository.save(
                                    application
                            );

                    return ResponseEntity.ok(updated);

                })
                .orElse(
                    ResponseEntity.notFound().build()
                );
    }


    // Delete application
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplication(
            @PathVariable Long id) {

        if (!applicationRepository.existsById(id)) {

            return ResponseEntity
                    .notFound()
                    .build();
        }

        applicationRepository.deleteById(id);

        return ResponseEntity.ok(
                "Application deleted successfully"
        );
    }
}