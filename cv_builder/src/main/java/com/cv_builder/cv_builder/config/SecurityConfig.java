package com.cv_builder.cv_builder.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/forgot-password").permitAll()
                        .requestMatchers("/set-password").permitAll()
                        .requestMatchers("/regenerate-otp").permitAll()
                        .requestMatchers("/verify-account").permitAll()
                        .requestMatchers("/api/events").permitAll()
                        .requestMatchers("/api/leave-applications").permitAll()
                        .requestMatchers("/api/blogposts").permitAll()
                        .requestMatchers("/api/reviews").permitAll()
                        .requestMatchers("/api/reviews/{id}").permitAll()
                        .requestMatchers("/api/applications").permitAll()
                        .requestMatchers("/api/applications/{id}").permitAll()
                        .requestMatchers("/api/jobs").permitAll()
                        .requestMatchers("/api/jobs/{id}").permitAll()
                        .anyRequest().authenticated()

                )
                //.formLogin(withDefaults())
                .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build());
        return manager;
    }


}