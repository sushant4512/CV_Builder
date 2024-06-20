package com.cv_builder.cv_builder.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EventDto {
    private String title;
    private String description;
    private LocalDate date;

}