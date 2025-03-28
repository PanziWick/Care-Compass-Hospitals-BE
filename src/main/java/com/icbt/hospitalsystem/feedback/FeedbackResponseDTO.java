package com.icbt.hospitalsystem.feedback;

import java.time.LocalDateTime;

public record FeedbackResponseDTO(
        Long id,
        Long patientId,
        String patientUsername,
        Long doctorId,
        String doctorUsername,
        String comment,
        int rating,
        LocalDateTime createdAt
) {
}
