package com.icbt.hospitalsystem.medicalrecord;

import java.time.LocalDateTime;

public record MedicalRecordResponseDTO(
        Long id,
        Long patientId,
        String patientName,
        Long doctorId,
        String doctorName,
        String diagnosis,
        String treatment,
        LocalDateTime createdAt
) {
    public MedicalRecordResponseDTO(MedicalRecord medicalRecord) {
        this(
                medicalRecord.getId(),
                medicalRecord.getPatient().getId(),
                medicalRecord.getPatient().getUser().getUsername(),
                medicalRecord.getDoctor().getId(),
                medicalRecord.getDoctor().getUser().getUsername(),
                medicalRecord.getDiagnosis(),
                medicalRecord.getTreatment(),
                medicalRecord.getCreatedAt()
        );
    }
}
