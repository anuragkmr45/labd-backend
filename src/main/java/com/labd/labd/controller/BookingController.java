package com.labd.labd.controller;

import com.labd.labd.dto.req.AddBookingRequest;
import com.labd.labd.dto.req.DeleteBookingRequest;
import com.labd.labd.dto.res.BookingResponse;
import com.labd.labd.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/add-booking")
    public ResponseEntity<String> addBooking(@RequestBody AddBookingRequest request, @RequestHeader("Authorization") String token) {
        bookingService.addBooking(request, token);
        return ResponseEntity.ok("Booking added");
    }

    @PostMapping("/cancel-booking")
    public ResponseEntity<String> deleteBooking(@RequestBody DeleteBookingRequest request, @RequestHeader("Authorization") String token) {
        bookingService.cancelBooking(request.getBookingId(), token);
        return ResponseEntity.ok("Booking cancelled");
    }

    @GetMapping("/my-bookings")
    public ResponseEntity<List<BookingResponse>> getAllBookings(@RequestHeader("Authorization") String token) {
        List<BookingResponse> bookings = bookingService.getAllBookings(token);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/track-booking/{bookingId}")
    public ResponseEntity<BookingResponse> trackBooking(@PathVariable Long bookingId, @RequestHeader("Authorization") String token) {
        BookingResponse booking = bookingService.trackBooking(bookingId, token);
        return ResponseEntity.ok(booking);
    }
}
