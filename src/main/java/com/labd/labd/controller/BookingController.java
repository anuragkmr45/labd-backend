package com.labd.labd.controller;

import com.labd.labd.dto.req.AddBookingRequest;
import com.labd.labd.dto.req.AuthRequest;
import com.labd.labd.dto.req.SignupRequest;
import com.labd.labd.dto.res.AuthResponse;
import com.labd.labd.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    // @Autowired
    // private AuthService authService;

    // public String booking(@RequestBody AddBookingRequest request) {
    //     return authService.booking(request.getAddressId(), request.getDate(), request.getTime(), request.getContactId(), request.getStatusId(), request.getPaymentId());
    // }
}
