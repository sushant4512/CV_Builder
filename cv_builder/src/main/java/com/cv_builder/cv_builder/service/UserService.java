package com.cv_builder.cv_builder.service;

import com.cv_builder.cv_builder.entity.User;
import com.cv_builder.cv_builder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        return userRepository.save(user);
    }

    public Optional<User> loginUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }

    public void generatePasswordResetToken(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            String token = UUID.randomUUID().toString();
            user.get().setResetToken(token);
            userRepository.save(user.get());
            // Send email with reset token (omitted for brevity)
        }
    }
    public Optional<User> resetPassword(String token, String newPassword) {
        Optional<User> user = userRepository.findByResetToken(token);
        if (user.isPresent()) {
            user.get().setPassword(passwordEncoder.encode(newPassword));
            user.get().setResetToken(null);
            return Optional.of(userRepository.save(user.get()));
        }
        return Optional.empty();
    }
}