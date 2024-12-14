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
    @Column(name = "service_id", nullable = false, unique = true)
    private Long serviceId;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String serviceDesc;

    @ElementCollection
    @CollectionTable(name = "test_parameters", joinColumns = @JoinColumn(name = "service_id"))
    @Column(name = "test_parameter")
    private List<String> testParameters;

    @Column(name = "sample_type", nullable = false)
    private String sampleType;

    @Column(name = "tube_type", nullable = false)
    private String tubeType;

    @Column(name = "package_includes", nullable = false, columnDefinition = "TEXT")
    private String packageIncludes;

    @Column(name = "discounted_price", nullable = false)
    private String discountedPrice;

    @Column(name = "discount_percentage", nullable = false)
    private String discountPercentage;

    @ManyToMany(mappedBy = "services", fetch = FetchType.LAZY)
    private List<BookingEntity> bookings;
}
