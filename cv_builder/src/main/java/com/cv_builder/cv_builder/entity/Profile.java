package com.cv_builder.cv_builder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String education;
    private String address;
    private Date dateOfBirth;
    private String projectDetails;
    private String preferredLocation;
    private Double salaryExpectation;
}
