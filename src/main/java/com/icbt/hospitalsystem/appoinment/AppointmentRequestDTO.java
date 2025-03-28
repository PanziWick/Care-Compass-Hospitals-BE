package com.icbt.hospitalsystem.appoinment;

import java.time.LocalDate;

public record AppointmentRequestDTO(
        Long patientId,
        Long doctorId,
        LocalDate appointmentDate
) {
}
