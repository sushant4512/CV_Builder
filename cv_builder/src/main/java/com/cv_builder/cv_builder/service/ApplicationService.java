package com.cv_builder.cv_builder.service;

import com.cv_builder.cv_builder.entity.Application;
import com.cv_builder.cv_builder.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id).orElse(null);
    }

    public Application createApplication(Application application, MultipartFile resume) throws IOException {
        application.setResume(resume.getBytes());
        return applicationRepository.save(application);
    }

    public Application updateApplication(Long id, Application applicationDetails, MultipartFile resume) throws IOException {
        Application application = applicationRepository.findById(id).orElse(null);
        if (application != null) {
            application.setApplicantName(applicationDetails.getApplicantName());
            application.setEmail(applicationDetails.getEmail());
            application.setJobId(applicationDetails.getJobId());
            application.setResume(resume.getBytes());
            return applicationRepository.save(application);
        }
        return null;
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }
}