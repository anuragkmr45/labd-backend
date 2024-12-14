package com.labd.labd.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labd.labd.dto.req.UpdateUserRequest;
import com.labd.labd.entity.UserEntity;
import com.labd.labd.repository.UserRepository;
import com.labd.labd.service.UserServices;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void deleteUser(String username) {
        // Find user by username
        Optional<UserEntity> userOpt = userRepository.findByEmail(username);
        if (userOpt.isPresent()) {
            userRepository.delete(userOpt.get());
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public UserEntity getProfile(String username) {
        // Fetch the user by username
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserEntity updateProfile(String username, UpdateUserRequest updateProfileRequest) {
        // Fetch the user by username
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (updateProfileRequest.getEmail() != null) {
            user.setEmail(updateProfileRequest.getEmail());
        }
        if (updateProfileRequest.getPhoneNumber() != null) {
            user.setPhoneNumber(updateProfileRequest.getPhoneNumber());
        }
        if (updateProfileRequest.getBloodgrp() != null) {
            user.setBloodgrp(updateProfileRequest.getBloodgrp());
        }
        if (updateProfileRequest.getDob() != null) {
            user.setDob(updateProfileRequest.getDob());
        }

        // Save the updated user to the database
        return userRepository.save(user);
    }

}
