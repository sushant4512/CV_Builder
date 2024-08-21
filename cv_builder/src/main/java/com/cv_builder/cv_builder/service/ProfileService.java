package com.cv_builder.cv_builder.service;

import com.cv_builder.cv_builder.entity.Profile;
import com.cv_builder.cv_builder.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Optional<Profile> getProfileById(Long id) {
        return profileRepository.findById(id);
    }

    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile updateProfile(Long id, Profile profileDetails) {
        Profile profile = profileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found"));

        profile.setName(profileDetails.getName());
        profile.setEmail(profileDetails.getEmail());
        profile.setEducation(profileDetails.getEducation());
        profile.setAddress(profileDetails.getAddress());
        profile.setDateOfBirth(profileDetails.getDateOfBirth());
        profile.setProjectDetails(profileDetails.getProjectDetails());
        profile.setPreferredLocation(profileDetails.getPreferredLocation());
        profile.setSalaryExpectation(profileDetails.getSalaryExpectation());

        return profileRepository.save(profile);
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }
}
