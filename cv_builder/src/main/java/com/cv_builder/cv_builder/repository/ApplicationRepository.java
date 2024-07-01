package com.cv_builder.cv_builder.repository;

import com.cv_builder.cv_builder.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
