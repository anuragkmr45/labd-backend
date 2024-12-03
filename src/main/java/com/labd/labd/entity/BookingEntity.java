package com.labd.labd.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bookings") // Table for BookingEntity
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "booking_id", nullable = false, unique = true)
    private long bookingId; // Primary Key

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // Foreign Key to UserEntity
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

    @Column(nullable = false,length = 50)
    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(nullable = false)
    private String collectionLocation;

    // Enums for statuses
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
