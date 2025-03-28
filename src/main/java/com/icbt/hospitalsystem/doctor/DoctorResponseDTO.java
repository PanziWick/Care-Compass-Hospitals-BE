package com.icbt.hospitalsystem.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponseDTO {
    private Long id;
    private String username;
    private String specialization;

    public DoctorResponseDTO(Doctor doctor) {
        this.id = doctor.getId();
        this.username = doctor.getUser().getUsername();
        this.specialization = doctor.getSpecialization();
    }
}
