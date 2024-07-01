package com.cv_builder.cv_builder.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String applicantName;
    private String email;
    private Long jobId;

    @Lob
    private byte[] resume;
}
