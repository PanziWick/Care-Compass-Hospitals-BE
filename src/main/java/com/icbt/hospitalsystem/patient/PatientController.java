package com.icbt.hospitalsystem.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<PatientResponseDTO> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public PatientResponseDTO getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @GetMapping("/user/{userId}")
    public PatientResponseDTO getPatientByUserId(@PathVariable Long userId) {
        return patientService.getPatientByUserId(userId);
    }
}
