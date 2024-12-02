package com.labd.labd.dto.req;

import lombok.Data;

@Data
public class AddBookingRequest {
    private String addressId;
    private String date;
    private String time;
    private String contactId;
    private String statusId;
    private String paymentId;
}
