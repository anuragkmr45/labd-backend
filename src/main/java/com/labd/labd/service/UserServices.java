package com.labd.labd.service;

import com.labd.labd.dto.req.UpdateUserRequest;
import com.labd.labd.entity.UserEntity;

public interface UserServices {
    void deleteUser(String username);
    UserEntity getProfile(String username);
    UserEntity updateProfile(String username, UpdateUserRequest updateProfileRequest);
}
