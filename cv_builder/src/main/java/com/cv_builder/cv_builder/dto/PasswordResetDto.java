package com.cv_builder.cv_builder.dto;


import lombok.Data;

@Data
public class PasswordResetDto {
    private String token;
    private String newPassword;
}