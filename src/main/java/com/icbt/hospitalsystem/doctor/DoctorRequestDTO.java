package com.icbt.hospitalsystem.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequestDTO {
    private Long userId;
    private String specialization;
}
