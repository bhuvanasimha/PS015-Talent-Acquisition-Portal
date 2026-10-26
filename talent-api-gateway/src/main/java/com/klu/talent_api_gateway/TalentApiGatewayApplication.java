package com.klu.talent_api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TalentApiGatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(
                TalentApiGatewayApplication.class,
                args
        );
    }
}