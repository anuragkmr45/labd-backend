package com.labd.labd.controller;

import com.labd.labd.dto.req.AuthRequest;
import com.labd.labd.dto.req.SignupRequest;
import com.labd.labd.dto.res.AuthResponse;
import com.labd.labd.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
// @Tag(name = "Auth APIs", description = "Handles user authentication.")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    // @Operation(summary = "Signup", description = "Register a new user.")
    public String signup(@RequestBody SignupRequest request) {
        return authService.signup(request.getName(), request.getEmail(), request.getPassword(), request.getPhoneNumber() , request.getDob(), request.getBloodgrp());
    }

    @PostMapping("/signin")
    // @Operation(summary = "Login", description = "Authenticate a user.")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }
 
    @PostMapping("/signout")
    // @Operation(summary = "Logout", description = "Log out a user.")
    public String logout() {
        return "Logout successful";
    }
}
