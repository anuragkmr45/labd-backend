package com.labd.labd.dto.res;

import lombok.Data;
import java.util.List;

@Data
public class ServiceDetails {
    private String serviceName;
    private List<String> testParameters;
}
