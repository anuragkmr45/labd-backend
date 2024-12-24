package com.labd.labd.dto.res;

import lombok.Data;

import java.util.List;

@Data
public class ServiceResponse {
    private Long serviceId;
    private String serviceName;
    private String serviceDesc;
    private List<String> testParameters;
    private String sampleType;
    private String tubeType;
    private String packageIncludes;
    private String discountedPrice;
    private String discountPercentage;
}
