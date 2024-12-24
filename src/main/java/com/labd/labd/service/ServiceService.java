package com.labd.labd.service;

import com.labd.labd.dto.res.ServiceResponse;
import com.labd.labd.entity.ServiceEntity;

import java.util.List;

public interface ServiceService {
    void addServices(List<ServiceEntity> services);

    void updateService(Long serviceId, ServiceEntity updatedService);

    List<ServiceResponse> getAllServices();
    ServiceResponse getServiceById(Long serviceId);

    void deleteService(Long serviceId);
    void deleteAllServices();

}
