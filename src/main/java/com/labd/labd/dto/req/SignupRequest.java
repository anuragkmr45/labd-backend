package com.labd.labd.dto.req;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignupRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be a valid format")
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(
        regexp = "^[0-9]{10}$",
        message = "Phone number must be exactly 10 digits"
    )
    private String phoneNumber;

    @NotBlank(message = "Password cannot be blank")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
        message = "Password must include one uppercase letter, one lowercase letter, one number, one special character, and be at least 8 characters long"
    )
    private String password;

    @Pattern(
        regexp = "^\\d{4}-\\d{2}-\\d{2}$",
        message = "Date of Birth must be in the format YYYY-MM-DD"
    )
    private String dob;

    @Pattern(
        regexp = "^(A|B|AB|O)[+-]$",
        message = "Blood group must be in the format A+, A-, B+, B-, AB+, AB-, O+, or O-"
    )
    private String bloodgrp;
}
