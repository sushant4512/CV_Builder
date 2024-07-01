package com.cv_builder.cv_builder.controller;

import com.cv_builder.cv_builder.entity.Application;
import com.cv_builder.cv_builder.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable Long id) {
        Application application = applicationService.getApplicationById(id);
        if (application == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(application);
    }

    @PostMapping
    public ResponseEntity<Application> createApplication(
            @RequestPart("application") Application application,
            @RequestPart("resume") MultipartFile resume) {
        try {
            Application createdApplication = applicationService.createApplication(application, resume);
            return ResponseEntity.ok(createdApplication);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Application> updateApplication(
            @PathVariable Long id,
            @RequestPart("application") Application applicationDetails,
            @RequestPart("resume") MultipartFile resume) {
        try {
            Application updatedApplication = applicationService.updateApplication(id, applicationDetails, resume);
            if (updatedApplication == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedApplication);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }
}