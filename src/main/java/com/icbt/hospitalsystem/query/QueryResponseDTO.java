package com.icbt.hospitalsystem.query;

import java.time.LocalDateTime;

public record QueryResponseDTO(
        Long id,
        Long patientId,
        String patientUsername,
        String subject,
        String message,
        boolean answered,
        LocalDateTime createdAt,
        LocalDateTime answeredAt,
        Long answeredById,
        String answeredByUsername
) {}