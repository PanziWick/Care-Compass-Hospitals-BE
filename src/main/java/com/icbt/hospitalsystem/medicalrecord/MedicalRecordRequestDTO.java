package com.icbt.hospitalsystem.medicalrecord;

public record MedicalRecordRequestDTO(
        Long patientId,
        Long doctorId,
        String diagnosis,
        String treatment
) {
}
