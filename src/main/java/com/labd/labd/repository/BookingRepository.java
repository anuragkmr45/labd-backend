package com.labd.labd.repository;

import com.labd.labd.entity.BookingEntity;
import com.labd.labd.entity.UserEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
     List<BookingEntity> findAllByUser(UserEntity user);
}
