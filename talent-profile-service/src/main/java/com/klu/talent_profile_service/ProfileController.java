package com.klu.talent_profile_service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileRepository profileRepository;

    public ProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    // CREATE PROFILE
    @PostMapping
    public ResponseEntity<Profile> createProfile(
            @RequestBody Profile profile) {

        if (profileRepository.existsByUserId(
                profile.getUserId())) {

            return ResponseEntity.badRequest().build();
        }

        Profile savedProfile =
                profileRepository.save(profile);

        return ResponseEntity.ok(savedProfile);
    }

    // GET ALL PROFILES
    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {

        return ResponseEntity.ok(
                profileRepository.findAll()
        );
    }

    // GET PROFILE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfile(
            @PathVariable Long id) {

        return profileRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(
                    ResponseEntity.notFound().build()
                );
    }

    // GET PROFILE BY USER ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<Profile> getProfileByUserId(
            @PathVariable Long userId) {

        return profileRepository
                .findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(
                    ResponseEntity.notFound().build()
                );
    }

    // UPDATE PROFILE
    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(
            @PathVariable Long id,
            @RequestBody Profile newProfile) {

        return profileRepository
                .findById(id)
                .map(profile -> {

                    profile.setUserId(
                            newProfile.getUserId()
                    );

                    profile.setFullName(
                            newProfile.getFullName()
                    );

                    profile.setPhone(
                            newProfile.getPhone()
                    );

                    profile.setLocation(
                            newProfile.getLocation()
                    );

                    profile.setSkills(
                            newProfile.getSkills()
                    );

                    profile.setEducation(
                            newProfile.getEducation()
                    );

                    profile.setExperience(
                            newProfile.getExperience()
                    );

                    Profile updated =
                            profileRepository.save(profile);

                    return ResponseEntity.ok(updated);

                })
                .orElse(
                    ResponseEntity.notFound().build()
                );
    }

    // DELETE PROFILE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfile(
            @PathVariable Long id) {

        if (!profileRepository.existsById(id)) {

            return ResponseEntity
                    .notFound()
                    .build();
        }

        profileRepository.deleteById(id);

        return ResponseEntity.ok(
                "Profile deleted successfully"
        );
    }
}