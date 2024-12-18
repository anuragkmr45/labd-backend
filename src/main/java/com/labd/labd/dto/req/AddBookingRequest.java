package com.labd.labd.dto.req;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddBookingRequest {

    @NotNull(message = "Date cannot be null")
    private String addressId;

    @NotNull(message = "Date cannot be null")
    private String date;

    @NotNull(message = "Date cannot be null")
    private String time;

    @NotNull(message = "Date cannot be null")
    private String contactId;

    @NotNull(message = "Date cannot be null")
    private String statusId;

    @NotNull(message = "Date cannot be null")
    private String paymentId;

    @NotEmpty(message = "At least one service must be selected")
    private List<@NotNull(message = "Service cannot be null") Long> services;
}
