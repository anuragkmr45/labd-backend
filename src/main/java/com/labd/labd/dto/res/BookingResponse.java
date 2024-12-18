package com.labd.labd.dto.res;

// import java.util.List;

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
    // private List<ServiceDetails> services;
}

// @Data
// class ServiceDetails {
//     private String serviceName;
//     private List<String> testParameters;
// }