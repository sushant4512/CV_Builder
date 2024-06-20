package com.cv_builder.cv_builder.controller;


import com.cv_builder.cv_builder.dto.PasswordResetDto;
import com.cv_builder.cv_builder.dto.PasswordResetRequestDto;
import com.cv_builder.cv_builder.dto.UserLoginDto;
import com.cv_builder.cv_builder.dto.UserRegistrationDto;
import com.cv_builder.cv_builder.entity.User;
import com.cv_builder.cv_builder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        userService.registerUser(registrationDto.getUsername(), registrationDto.getPassword(), registrationDto.getEmail());
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDto loginDto) {
        Optional<User> user = userService.loginUser(loginDto.getUsername(), loginDto.getPassword());
        if (user.isPresent()) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody PasswordResetRequestDto requestDto) {
        userService.generatePasswordResetToken(requestDto.getEmail());
        return ResponseEntity.ok("Password reset token sent to email");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetDto resetDto) {
        Optional<User> user = userService.resetPassword(resetDto.getToken(), resetDto.getNewPassword());
        if (user.isPresent()) {
            return ResponseEntity.ok("Password reset successful");
        }
        return ResponseEntity.status(400).body("Invalid token");
    }
}