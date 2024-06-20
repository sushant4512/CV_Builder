package com.cv_builder.cv_builder.controller;

import com.cv_builder.cv_builder.dto.EventDto;
import com.cv_builder.cv_builder.dto.LeaveApplicationDto;
import com.cv_builder.cv_builder.entity.Event;
import com.cv_builder.cv_builder.entity.LeaveApplication;
import com.cv_builder.cv_builder.service.EventService;
import com.cv_builder.cv_builder.service.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private EventService eventService;

    @Autowired
    private LeaveApplicationService leaveApplicationService;

    @PostMapping("/events")
    public ResponseEntity<Event> createEvent(@RequestBody EventDto eventDto) {
        Event event = new Event();
        event.setTitle(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        event.setDate(eventDto.getDate());
        return ResponseEntity.ok(eventService.createEvent(event));
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @PostMapping("/leave-applications")
    public ResponseEntity<LeaveApplication> applyLeave(@RequestBody LeaveApplicationDto leaveApplicationDto) {
        LeaveApplication leaveApplication = new LeaveApplication();
        leaveApplication.setEmployeeId(leaveApplicationDto.getEmployeeId());
        leaveApplication.setStartDate(leaveApplicationDto.getStartDate());
        leaveApplication.setEndDate(leaveApplicationDto.getEndDate());
        leaveApplication.setReason(leaveApplicationDto.getReason());
        return ResponseEntity.ok(leaveApplicationService.applyLeave(leaveApplication));
    }

    @GetMapping("/leave-applications")
    public ResponseEntity<List<LeaveApplication>> getAllLeaveApplications() {
        return ResponseEntity.ok(leaveApplicationService.getAllLeaveApplications());
    }

    @PutMapping("/leave-applications/{id}")
    public ResponseEntity<LeaveApplication> updateLeaveStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(leaveApplicationService.updateLeaveStatus(id, status));
    }
}