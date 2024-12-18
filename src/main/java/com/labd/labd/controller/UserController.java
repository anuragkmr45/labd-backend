package com.labd.labd.controller;

import com.labd.labd.dto.req.UpdateUserRequest;
import com.labd.labd.entity.UserEntity;
import com.labd.labd.service.UserServices;
import com.labd.labd.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
public class UserController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private JwtUtil jwtUtil;

    @DeleteMapping()
    public ResponseEntity<String> deleteProfile(@RequestHeader("Authorization") String token) {
        // Extract username from token
        String username = jwtUtil.extractEmail(token.substring(7));
        userServices.deleteUser(username);
        return ResponseEntity.ok("Profile deleted successfully.");
    }

    @GetMapping()
    public ResponseEntity<UserEntity> myProfile(@RequestHeader("Authorization") String token) {
        // Extract username from token
        String username = jwtUtil.extractEmail(token.substring(7));
        // Fetch the user profile using the service
        UserEntity user = userServices.getProfile(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping()
    public ResponseEntity<UserEntity> updateProfile(
            @RequestHeader("Authorization") String token,
            @RequestBody UpdateUserRequest updateProfileRequest) {
        // Extract username from token
        String username = jwtUtil.extractEmail(token.substring(7));
        // Update the user's profile using the service
        UserEntity updatedUser = userServices.updateProfile(username, updateProfileRequest);
        return ResponseEntity.ok(updatedUser);
    }

}
