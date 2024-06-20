package com.cv_builder.cv_builder.service;

import com.cv_builder.cv_builder.entity.LeaveApplication;
import com.cv_builder.cv_builder.repository.LeaveApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveApplicationService {
    @Autowired
    private LeaveApplicationRepository leaveApplicationRepository;

    public LeaveApplication applyLeave(LeaveApplication leaveApplication) {
        leaveApplication.setStatus("PENDING");
        return leaveApplicationRepository.save(leaveApplication);
    }

    public List<LeaveApplication> getAllLeaveApplications() {
        return leaveApplicationRepository.findAll();
    }

    public LeaveApplication updateLeaveStatus(Long id, String status) {
        LeaveApplication leaveApplication = leaveApplicationRepository.findById(id).orElseThrow();
        leaveApplication.setStatus(status);
        return leaveApplicationRepository.save(leaveApplication);
    }
}