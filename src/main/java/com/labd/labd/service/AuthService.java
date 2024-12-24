package com.labd.labd.service;

import com.labd.labd.dto.req.SigninRequest;
import com.labd.labd.dto.res.SigninResponse;
import com.labd.labd.dto.res.SignupResponse;
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

    public SignupResponse signup(@Valid String name, String email, String password, String phoneNumber ,String dob, String bloodgrp) {
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
        String token = jwtUtil.generateToken(email);
        SignupResponse response = new SignupResponse();
        response.setToken(token);
        return response;
    }

    public SigninResponse login(SigninRequest request) {
        UserEntity user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        SigninResponse response = new SigninResponse();
        response.setToken(token);
        return response;
    }
}
