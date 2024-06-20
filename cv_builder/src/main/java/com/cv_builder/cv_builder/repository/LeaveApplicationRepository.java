package com.cv_builder.cv_builder.repository;

import com.cv_builder.cv_builder.entity.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long> {
}