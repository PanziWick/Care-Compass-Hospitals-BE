package com.icbt.hospitalsystem.hospitalservice;

import java.math.BigDecimal;

public record HospitalServiceRequestDTO(
        String serviceName,
        String description,
        BigDecimal price
) {
}
