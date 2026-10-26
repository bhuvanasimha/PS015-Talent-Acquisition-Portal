package com.klu.talent_job_service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // CREATE JOB
    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {

        Job savedJob = jobRepository.save(job);

        return ResponseEntity.ok(savedJob);
    }

    // GET ALL JOBS
    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {

        return ResponseEntity.ok(jobRepository.findAll());
    }

    // GET JOB BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {

        return jobRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE JOB
    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(
            @PathVariable Long id,
            @RequestBody Job updatedJob) {

        return jobRepository.findById(id)
                .map(job -> {

                    job.setTitle(updatedJob.getTitle());
                    job.setDescription(updatedJob.getDescription());
                    job.setCompany(updatedJob.getCompany());
                    job.setLocation(updatedJob.getLocation());
                    job.setSalary(updatedJob.getSalary());
                    job.setRecruiterId(updatedJob.getRecruiterId());

                    Job savedJob = jobRepository.save(job);

                    return ResponseEntity.ok(savedJob);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE JOB
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {

        if (!jobRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        jobRepository.deleteById(id);

        return ResponseEntity.ok("Job deleted successfully");
    }
}