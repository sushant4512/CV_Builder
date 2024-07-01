package com.cv_builder.cv_builder.service;

import com.cv_builder.cv_builder.entity.Job;
import com.cv_builder.cv_builder.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public Job updateJob(Long id, Job jobDetails) {
        Job job = jobRepository.findById(id).orElse(null);
        if (job != null) {
            job.setTitle(jobDetails.getTitle());
            job.setDescription(jobDetails.getDescription());
            job.setLocation(jobDetails.getLocation());
            job.setCompany(jobDetails.getCompany());
            return jobRepository.save(job);
        }
        return null;
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
