package com.labd.labd.service.booking;

import com.labd.labd.dto.req.AuthRequest;
import com.labd.labd.dto.res.AuthResponse;
import com.labd.labd.entity.UserEntity;
import com.labd.labd.repository.UserRepository;
import com.labd.labd.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AddBookingService {
    // @Autowired
    // private UserRepository userRepository;

    // @Autowired
    // private JwtUtil jwtUtil;

    // public String addBooking(String addressId, String date, String time, String contactId, String statusId, String paymentId) {
    //     UserEntity user = new UserEntity();
    //     user.setName(name);
    //     user.setEmail(email);
    //     user.setPassword(passwordEncoder.encode(password));
    //     userRepository.save(user);
    //     return jwtUtil.generateToken(email);
    // }

    // public AuthResponse login(AuthRequest request) {
    //     UserEntity user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Invalid credentials"));
    //     if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
    //         throw new RuntimeException("Invalid credentials");
    //     }
    //     String token = jwtUtil.generateToken(user.getEmail());
    //     AuthResponse response = new AuthResponse();
    //     response.setToken(token);
    //     return response;
    // }
}
