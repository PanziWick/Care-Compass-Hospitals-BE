package com.icbt.hospitalsystem.patient;

import com.icbt.hospitalsystem.common.ResourceNotFoundException;
import com.icbt.hospitalsystem.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    private static final String PATIENT_NOT_FOUND = "Patient not found";

    public List<PatientResponseDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(PatientResponseDTO::new)
                .collect(Collectors.toList());
    }

    public PatientResponseDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PATIENT_NOT_FOUND));

        return new PatientResponseDTO(
                patient.getId(),
                patient.getUser().getUsername(),
                patient.getDateOfBirth(),
                patient.getGenderEnum(),
                patient.getMedicalHistory()
        );
    }

    public PatientResponseDTO getPatientByUserId(Long userId) {
        Patient patient = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException(PATIENT_NOT_FOUND));

        return new PatientResponseDTO(
                patient.getId(),
                patient.getUser().getUsername(),
                patient.getDateOfBirth(),
                patient.getGenderEnum(),
                patient.getMedicalHistory()
        );
    }
}
