package com.labd.labd.entity;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "users", indexes = {@Index(name = "idx_email", columnList = "email")})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true)
    private Long id;
    
    @Column(nullable = false)
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Column(unique = true, nullable = false)
    @Email(message = "Invalid email format")
    private String email;
    
    @Column(unique = true, nullable = false)
    @Pattern(
        regexp = "^[0-9]{10}$",
        message = "Phone number must be exactly 10 digits"
    )
    private String phoneNumber;
    
    @Column(nullable = false, unique = false)
    // @Pattern(
    //     regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
    //     message = "Password must be at least 8 characters long, include one uppercase letter, one lowercase letter, one number, and one special character."
    // )
    private String password;
    
    @Column()
    @Pattern(
        regexp = "^\\d{4}-\\d{2}-\\d{2}$",
        message = "Date of Birth must be in the format YYYY-MM-DD"
    )
    private String dob;
    
    @Column()
    @Pattern(
        regexp = "^(A|B|AB|O)[+-]$",
        message = "Blood group must be in the format A+, A-, B+, B-, AB+, AB-, O+, or O-"
    )
    private String bloodgrp;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    private List<BookingEntity> bookings;
}
