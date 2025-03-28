package com.icbt.hospitalsystem.medicalrecord;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @PostMapping
    public ResponseEntity<MedicalRecordResponseDTO> createMedicalRecord(@RequestBody MedicalRecordRequestDTO request) {
        return ResponseEntity.ok(medicalRecordService.createMedicalRecord(request));
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecordResponseDTO>> getAllMedicalRecords() {
        return ResponseEntity.ok(medicalRecordService.getAllMedicalRecords());
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalRecordResponseDTO>> getMedicalRecordsByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(medicalRecordService.getMedicalRecordsByPatientId(patientId));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<MedicalRecordResponseDTO>> getMedicalRecordsByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(medicalRecordService.getMedicalRecordsByDoctorId(doctorId));
    }
}
