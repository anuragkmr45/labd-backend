package com.labd.labd.controller;

import com.labd.labd.dto.res.ServiceResponse;
import com.labd.labd.entity.ServiceEntity;
import com.labd.labd.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping
    public ResponseEntity<String> addServices(@RequestBody List<ServiceEntity> services) {
        serviceService.addServices(services);
        return ResponseEntity.ok("Services added successfully");
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<String> updateService(@PathVariable Long serviceId,
            @RequestBody ServiceEntity updatedService) {
        serviceService.updateService(serviceId, updatedService);
        return ResponseEntity.ok("Service updated successfully");
    }

    @GetMapping
    public ResponseEntity<List<ServiceResponse>> getAllServices() {
        return ResponseEntity.ok(serviceService.getAllServices());
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceResponse> getServiceById(@PathVariable Long serviceId) {
        return ResponseEntity.ok(serviceService.getServiceById(serviceId));
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<String> deleteService(@PathVariable Long serviceId) {
        serviceService.deleteService(serviceId);
        return ResponseEntity.ok("Service deleted successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllServices() {
        serviceService.deleteAllServices();
        return ResponseEntity.ok("All services deleted successfully");
    }
}
