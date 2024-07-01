package com.cv_builder.cv_builder.repository;

import com.cv_builder.cv_builder.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

}
