package com.cv_builder.cv_builder.service;

import com.cv_builder.cv_builder.entity.Event;
import com.cv_builder.cv_builder.entity.LeaveApplication;
import com.cv_builder.cv_builder.repository.EventRepository;
import com.cv_builder.cv_builder.repository.LeaveApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

}

