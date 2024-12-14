package com.labd.labd.dto.req;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String email;
    private String phoneNumber;
    private String password;
    private String dob;
    private String bloodgrp;
}

