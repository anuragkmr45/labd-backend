package com.labd.labd.service.impl;

import com.labd.labd.dto.req.AddBookingRequest;
import com.labd.labd.dto.res.BookingResponse;
import com.labd.labd.entity.BookingEntity;
import com.labd.labd.entity.UserEntity;
import com.labd.labd.repository.BookingRepository;
import com.labd.labd.repository.UserRepository;
import com.labd.labd.service.BookingService;
import com.labd.labd.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void addBooking(AddBookingRequest request, String token) {
        String userEmail = jwtUtil.extractEmail(token.substring(7));
        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BookingEntity booking = new BookingEntity();  
        booking.setUser(user);
        booking.setDate(request.getDate());
        booking.setTime(request.getTime());
        booking.setCollectionLocation(request.getAddressId());
        booking.setReportStatus(BookingEntity.ReportStatus.BOOKING_DONE);
        booking.setPaymentStatus(BookingEntity.PaymentStatus.PENDING);

        bookingRepository.save(booking);
    }

    @Override
    public void cancelBooking(Long bookingId, String token) {
        BookingEntity booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        bookingRepository.delete(booking);
    }

    @Override
    public List<BookingResponse> getAllBookings(String token) {
        String userEmail = jwtUtil.extractEmail(token.substring(7));
        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getBookings().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponse trackBooking(Long bookingId, String token) {
        BookingEntity booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return mapToResponse(booking);
    }

    private BookingResponse mapToResponse(BookingEntity booking) {
        BookingResponse response = new BookingResponse();
        response.setBookingId(booking.getBookingId());
        response.setUserId(booking.getUser().getId().toString());
        response.setDate(booking.getDate());
        response.setTime(booking.getTime());
        response.setReportStatus(booking.getReportStatus().toString());
        response.setPaymentStatus(booking.getPaymentStatus().toString());
        response.setCollectionLocation(booking.getCollectionLocation());
        return response;
    }
}
