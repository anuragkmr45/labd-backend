package com.labd.labd.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labd.labd.dto.res.ServiceResponse;
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
    public List<ServiceResponse> getAllServices() {
        return serviceRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceResponse getServiceById(Long serviceId) {
        ServiceEntity service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        return mapToDTO(service);
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

    private ServiceResponse mapToDTO(ServiceEntity service) {
        ServiceResponse dto = new ServiceResponse();
        dto.setServiceId(service.getServiceId());
        dto.setServiceName(service.getServiceName());
        dto.setServiceDesc(service.getServiceDesc());
        dto.setTestParameters(service.getTestParameters());
        dto.setSampleType(service.getSampleType());
        dto.setTubeType(service.getTubeType());
        dto.setPackageIncludes(service.getPackageIncludes());
        dto.setDiscountedPrice(service.getDiscountedPrice());
        dto.setDiscountPercentage(service.getDiscountPercentage());
        return dto;
    }

}
