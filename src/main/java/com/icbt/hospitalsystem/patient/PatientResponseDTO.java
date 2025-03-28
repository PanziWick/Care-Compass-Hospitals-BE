package com.icbt.hospitalsystem.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDTO {
    private Long id;
    private String username;
    private LocalDate dateOfBirth;
    private GenderEnum genderEnum;
    private String medicalHistory;

    public PatientResponseDTO(Patient patient) {
        this.id = patient.getId();
        this.username = patient.getUser().getUsername();
        this.dateOfBirth = patient.getDateOfBirth();
        this.genderEnum = patient.getGenderEnum();
        this.medicalHistory = patient.getMedicalHistory();
    }
}
