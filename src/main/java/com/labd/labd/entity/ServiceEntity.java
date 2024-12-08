package com.labd.labd.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "services")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    @Column(nullable = false)
    private String serviceName;

    @Column(nullable = false)
    private String desc;

    @Column(nullable = false)
    private Double price;

    // @ManyToMany(mappedBy = "services", fetch = FetchType.LAZY)
    // private List<BookingEntity> bookings;
}
