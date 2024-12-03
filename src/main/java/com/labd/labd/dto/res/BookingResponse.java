package com.labd.labd.dto.res;

import lombok.Data;

@Data
public class BookingResponse {
    private Long bookingId;
    private String userId;
    private String services;
    private String date;
    private String time;
    private String reportStatus;
    private String paymentStatus;
    private String collectionLocation;
}
