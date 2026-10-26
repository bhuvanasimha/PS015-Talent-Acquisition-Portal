package com.klu.talent_application_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "PROFILE-SERVICE"
)
public interface ProfileClient {

    @GetMapping("/profiles/{userId}")
    ProfileResponse getProfileByUserId(
            @PathVariable("userId") Long userId
    );
}