package com.labd.labd.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bookings")
public class ServiceEntity {
    private String bookingId;
    private String userId;
    private List<String> serviceId;
    private String date;
    private String time;
    private String reportStatus;
    private String paymentStatus;
    private String colleectionLocation;   
}
