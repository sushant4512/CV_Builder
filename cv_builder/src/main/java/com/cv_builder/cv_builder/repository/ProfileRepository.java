package com.cv_builder.cv_builder.repository;

import com.cv_builder.cv_builder.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
