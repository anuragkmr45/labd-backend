package com.labd.labd.entity;

import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // Foreign key to UserEntity
    private UserEntity user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "booking_services", // Join table for bookings and services
        joinColumns = @JoinColumn(name = "booking_id"),
        inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<ServiceEntity> services; // Many-to-Many relationship with ServiceEntity

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

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
