package com.klu.talent_job_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TalentJobServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalentJobServiceApplication.class, args);
    }
}