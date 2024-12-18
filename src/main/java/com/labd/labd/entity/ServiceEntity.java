package com.labd.labd.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Service name cannot be blank")
    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @NotBlank(message = "Description cannot be blank")
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String serviceDesc;

    @ElementCollection
    @CollectionTable(name = "test_parameters", joinColumns = @JoinColumn(name = "service_id"))
    @Size(min = 1, message = "At least one test parameter is required")
    @Column(name = "test_parameter")
    private List<@NotBlank(message = "Test parameter cannot be blank") String> testParameters;

    @NotBlank(message = "Sample type cannot be blank")
    @Column(name = "sample_type", nullable = false)
    private String sampleType;

    @NotBlank(message = "Tube type cannot be blank")
    @Column(name = "tube_type", nullable = false)
    private String tubeType;

    @NotBlank(message = "Package includes cannot be blank")
    @Column(name = "package_includes", nullable = false, columnDefinition = "TEXT")
    private String packageIncludes;

    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Discounted price must be a valid number")
    @NotBlank(message = "Discounted price cannot be blank")
    @Column(name = "discounted_price", nullable = false)
    private String discountedPrice;

    @Pattern(regexp = "^(100|[1-9][0-9]?|0)$", message = "Discount percentage must be between 0 and 100")
    @NotBlank(message = "Discount percentage cannot be blank")
    @Column(name = "discount_percentage", nullable = false)
    private String discountPercentage;

    @ManyToMany(mappedBy = "services", fetch = FetchType.LAZY)
    private List<BookingEntity> bookings;
}
