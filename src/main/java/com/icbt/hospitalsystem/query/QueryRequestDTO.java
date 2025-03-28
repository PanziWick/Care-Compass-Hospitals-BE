package com.icbt.hospitalsystem.query;

public record QueryRequestDTO(
        Long patientId,
        String subject,
        String message
) {}
