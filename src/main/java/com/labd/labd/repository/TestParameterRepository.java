package com.labd.labd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labd.labd.entity.TestParameterEntity;

public interface TestParameterRepository extends JpaRepository<TestParameterEntity, Long> {
    List<TestParameterEntity> findByServiceId(Long serviceId);
}
