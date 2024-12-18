package com.labd.labd.service.impl;

import com.labd.labd.dto.req.AddBookingRequest;
import com.labd.labd.dto.res.BookingResponse;
import com.labd.labd.entity.BookingEntity;
import com.labd.labd.entity.ServiceEntity;
import com.labd.labd.entity.TestParameterEntity;
import com.labd.labd.entity.UserEntity;
import com.labd.labd.repository.BookingRepository;
import com.labd.labd.repository.ServiceRepository;
import com.labd.labd.repository.TestParameterRepository;
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
    private TestParameterRepository testParameterRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void addBooking(AddBookingRequest request, String token) {
        String userEmail = jwtUtil.extractEmail(token.substring(7));
        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getServices() == null || request.getServices().isEmpty()) {
            throw new RuntimeException("At least one service must be selected");
        }

        List<ServiceEntity> serviceEntities = serviceRepository.findAllById(request.getServices());
        if (serviceEntities.size() != request.getServices().size()) {
            throw new RuntimeException("One or more services are invalid");
        }

        BookingEntity booking = new BookingEntity();
        booking.setUser(user);
        booking.setDate(request.getDate());
        booking.setTime(request.getTime());
        booking.setCollectionLocation(request.getAddressId());
        booking.setServices(serviceEntities);
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

        List<BookingEntity> bookings = bookingRepository.findAllByUser(user);

        return bookings.stream()
                .map(this::mapToResponseWithServices)
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponse trackBooking(Long bookingId, String token) {
        BookingEntity booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return mapToResponseWithServices(booking);
    }


private BookingResponse mapToResponseWithServices(BookingEntity booking) {
    BookingResponse response = new BookingResponse();
    response.setBookingId(booking.getBookingId());
    response.setUserId(String.valueOf(booking.getUser().getId()));
    response.setDate(booking.getDate());
    response.setTime(booking.getTime());
    response.setReportStatus(booking.getReportStatus().toString());
    response.setPaymentStatus(booking.getPaymentStatus().toString());
    response.setCollectionLocation(booking.getCollectionLocation());

    // Fetch services and their test parameters
    List<com.labd.labd.dto.res.ServiceDetails> serviceDetails = booking.getServices().stream().map(service -> {
        com.labd.labd.dto.res.ServiceDetails details = new com.labd.labd.dto.res.ServiceDetails();
        details.setServiceName(service.getServiceName());

        // Fetch test parameters for this service
        List<String> testParameters = testParameterRepository.findByServiceId(service.getServiceId())
                .stream()
                .map(TestParameterEntity::getTestParameter)
                .collect(Collectors.toList());
        details.setTestParameters(testParameters);

        return details;
    }).collect(Collectors.toList());

    response.setServices(serviceDetails);
    return response;
}


}
