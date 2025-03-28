package com.icbt.hospitalsystem.feedback;

public record FeedbackRequestDTO(
        Long patientId,
        Long doctorId,
        String comment,
        int rating
) {
}
