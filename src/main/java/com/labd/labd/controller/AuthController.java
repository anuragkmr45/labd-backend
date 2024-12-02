package com.labd.labd.controller;

import com.labd.labd.dto.req.AuthRequest;
import com.labd.labd.dto.req.SignupRequest;
import com.labd.labd.dto.res.AuthResponse;
import com.labd.labd.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
        return authService.signup(request.getName(), request.getEmail(), request.getPassword());
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }
 
    @PostMapping("/logout")
    public String logout() {
        return "Logout successful";
    }
}
