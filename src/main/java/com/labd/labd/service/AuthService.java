package com.labd.labd.service;

import com.labd.labd.dto.req.AuthRequest;
import com.labd.labd.dto.res.AuthResponse;
import com.labd.labd.entity.UserEntity;
import com.labd.labd.repository.UserRepository;
import com.labd.labd.util.JwtUtil;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String signup(@Valid String name, String email, String password, String phoneNumber ,String dob, String bloodgrp) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        UserEntity user = new UserEntity();

        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhoneNumber(phoneNumber);
        user.setDob(dob);
        user.setBloodgrp(bloodgrp);
        
        userRepository.save(user);
        return jwtUtil.generateToken(email);
    }

    public AuthResponse login(AuthRequest request) {
        UserEntity user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        return response;
    }
}
