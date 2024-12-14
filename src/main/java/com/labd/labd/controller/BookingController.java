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
@RequestMapping("/api/v1")
// @Tag(name = "Booking APIs", description = "Handles booking-related operations.")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/add-booking")
    // @Operation(summary = "Add Booking", description = "Create a new booking.")
    public ResponseEntity<String> addBooking(@RequestBody AddBookingRequest request, @RequestHeader("Authorization") String token) {
        bookingService.addBooking(request, token);
        return ResponseEntity.ok("Booking added");
    }

    @PostMapping("/cancel-booking")
    // @Operation(summary = "Cancel Booking", description = "Cancel an existing booking.")
    public ResponseEntity<String> deleteBooking(@RequestBody DeleteBookingRequest request, @RequestHeader("Authorization") String token) {
        bookingService.cancelBooking(request.getBookingId(), token);
        return ResponseEntity.ok("Booking cancelled");
    }

    @GetMapping("/my-bookings")
    // @Operation(summary = "My Bookings", description = "Retrieve all bookings for the logged-in user.")
    public ResponseEntity<List<BookingResponse>> getAllBookings(@RequestHeader("Authorization") String token) {
        List<BookingResponse> bookings = bookingService.getAllBookings(token);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/track-booking/{bookingId}")
    // @Operation(summary = "Track Booking", description = "Track a specific booking by its ID.")
    public ResponseEntity<BookingResponse> trackBooking(@PathVariable Long bookingId, @RequestHeader("Authorization") String token) {
        BookingResponse booking = bookingService.trackBooking(bookingId, token);
        return ResponseEntity.ok(booking);
    }
}
