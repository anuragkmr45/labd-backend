package com.labd.labd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labd.labd.entity.ServiceEntity;
import com.labd.labd.repository.ServiceRepository;
import com.labd.labd.service.ServiceService;

@Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public void addServices(List<ServiceEntity> services) {
        serviceRepository.saveAll(services);
    }

    @Override
    public void updateService(Long serviceId, ServiceEntity updatedService) {
        ServiceEntity existingService = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        existingService.setServiceName(updatedService.getServiceName());
        existingService.setServiceDesc(updatedService.getServiceDesc());
        existingService.setTestParameters(updatedService.getTestParameters());
        existingService.setSampleType(updatedService.getSampleType());
        existingService.setTubeType(updatedService.getTubeType());
        existingService.setPackageIncludes(updatedService.getPackageIncludes());
        existingService.setDiscountedPrice(updatedService.getDiscountedPrice());
        existingService.setDiscountPercentage(updatedService.getDiscountPercentage());
        serviceRepository.save(existingService);
    }

    @Override
    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public ServiceEntity getServiceById(Long serviceId) {
        return serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));
    }

    @Override
    public void deleteService(Long serviceId) {
        if (!serviceRepository.existsById(serviceId)) {
            throw new RuntimeException("Service not found");
        }
        serviceRepository.deleteById(serviceId);
    }

    @Override
    public void deleteAllServices() {
        serviceRepository.deleteAll();
    }

}
