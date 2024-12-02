package com.labd.labd.entity;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bookings")
public class BookingEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String bookingId;
    
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, unique = true)
    @ElementCollection
    private List<String> serviceIds;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(nullable = false)
    private String collectionLocation;

    public enum ReportStatus {
        SAMPLE_COLLECTED,
        SAMPLE_RECEIVED,
        SAMPLE_REVIEWED,
        REPORT_DELIVERED
    }

    // Enum for Payment Status
    public enum PaymentStatus {
        PENDING,
        DONE,
        PROCESSING
    }
}
