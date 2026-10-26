package com.klu.talent_application_service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;

    private Long applicantId;

    private String status;

    private String appliedDate;


    public JobApplication() {
    }


    public JobApplication(Long jobId,
                          Long applicantId,
                          String status,
                          String appliedDate) {

        this.jobId = jobId;
        this.applicantId = applicantId;
        this.status = status;
        this.appliedDate = appliedDate;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getJobId() {
        return jobId;
    }


    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }


    public Long getApplicantId() {
        return applicantId;
    }


    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getAppliedDate() {
        return appliedDate;
    }


    public void setAppliedDate(String appliedDate) {
        this.appliedDate = appliedDate;
    }
}