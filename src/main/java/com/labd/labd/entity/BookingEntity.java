package com.labd.labd.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "bookings")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "booking_id", nullable = false, unique = true)
    private long bookingId;

    @NotNull(message = "User cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // Foreign key to UserEntity
    private UserEntity user;

    @NotEmpty(message = "At least one service must be selected")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "booking_services", // Join table for bookings and services
        joinColumns = @JoinColumn(name = "booking_id"),
        inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<@NotNull(message = "Service cannot be null") ServiceEntity> services;

    @NotBlank(message = "Date cannot be blank")
    @Pattern(
        regexp = "^\\d{4}-\\d{2}-\\d{2}$",
        message = "Date must be in the format YYYY-MM-DD"
    )
    @Column(nullable = false)
    private String date;

    @NotBlank(message = "Time cannot be blank")
    @Pattern(
        regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$",
        message = "Time must be in the format HH:mm (24-hour)"
    )
    @Column(nullable = false)
    private String time;

    @NotNull(message = "Report status cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ReportStatus reportStatus;

    @NotNull(message = "Payment status cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    @NotBlank(message = "Collection location cannot be blank")
    @Column(nullable = false)
    private String collectionLocation;

    public enum ReportStatus {
        BOOKING_DONE,
        SAMPLE_COLLECTED,
        SAMPLE_RECEIVED,
        SAMPLE_REVIEWED,
        REPORT_DELIVERED
    }

    public enum PaymentStatus {
        PENDING,
        DONE,
        PROCESSING
    }
}
