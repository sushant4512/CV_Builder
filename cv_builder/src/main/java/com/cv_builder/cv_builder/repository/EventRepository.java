package com.cv_builder.cv_builder.repository;

import com.cv_builder.cv_builder.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

}
