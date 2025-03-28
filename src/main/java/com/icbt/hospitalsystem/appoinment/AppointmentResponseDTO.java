package com.icbt.hospitalsystem.appoinment;

import java.time.LocalDate;

public record AppointmentResponseDTO(
        Long id,
        Long patientId,
        Long doctorId,
        LocalDate appointmentDate,
        AppointmentStatusEnum status
) {
    public AppointmentResponseDTO(Appointment appointment) {
        this(
                appointment.getId(),
                appointment.getPatient().getId(),
                appointment.getDoctor().getId(),
                appointment.getAppointmentDate(),
                appointment.getStatus()
        );
    }
}
