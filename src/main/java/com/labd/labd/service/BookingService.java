package com.labd.labd.service;

import com.labd.labd.dto.req.AddBookingRequest;
import com.labd.labd.dto.res.BookingResponse;

// import java.util.List;

public interface BookingService {
    void addBooking(AddBookingRequest request, String token);

    void cancelBooking(Long bookingId, String token);

    // List<BookingResponse> getAllBookings(String token);

    BookingResponse trackBooking(Long bookingId, String token);
}
