package com.icbt.hospitalsystem.hospitalservice;

import java.math.BigDecimal;

public record HospitalServiceResponseDTO(
        Long id,
        String serviceName,
        String description,
        BigDecimal price) {
    public HospitalServiceResponseDTO(HospitalServiceEntity service) {
        this(service.getId(), service.getServiceName(), service.getDescription(), service.getPrice());
    }
}