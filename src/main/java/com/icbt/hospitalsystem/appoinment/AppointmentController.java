package com.icbt.hospitalsystem.appoinment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> bookAppointment(@RequestBody AppointmentRequestDTO request) {
        return ResponseEntity.ok(appointmentService.bookAppointment(request));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

//    @GetMapping("/patient/{userId}")
//    public ResponseEntity<List<AppointmentResponseDTO>> getPatientAppointmentsByUserId(@PathVariable Long userId) {
//        return ResponseEntity.ok(appointmentService.getPatientAppointmentsByUserId(userId));
//    }

    @GetMapping("/doctor/{userId}")
    public ResponseEntity<List<AppointmentResponseDTO>> getDoctorAppointmentsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(appointmentService.getDoctorAppointmentsByUserId(userId));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatientId(patientId));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByDoctorId(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctorId(doctorId));
    }


    @PatchMapping("/{id}/status")
    public ResponseEntity<AppointmentResponseDTO> updateAppointmentStatus(
            @PathVariable Long id,
            @RequestParam AppointmentStatusEnum status
    ) {
        return ResponseEntity.ok(appointmentService.updateAppointmentStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return ResponseEntity.ok("Appointment canceled successfully.");
    }
}
