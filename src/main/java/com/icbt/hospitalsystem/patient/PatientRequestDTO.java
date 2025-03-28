package com.icbt.hospitalsystem.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDTO {
    private Long userId;
    private LocalDate dateOfBirth;
    private GenderEnum genderEnum;
    private String medicalHistory;
}
