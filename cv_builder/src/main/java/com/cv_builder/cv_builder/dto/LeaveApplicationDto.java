package com.cv_builder.cv_builder.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveApplicationDto {
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
}