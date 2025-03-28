package com.icbt.hospitalsystem.medicalrecord;

import com.icbt.hospitalsystem.doctor.Doctor;
import com.icbt.hospitalsystem.doctor.DoctorRepository;
import com.icbt.hospitalsystem.patient.Patient;
import com.icbt.hospitalsystem.patient.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public MedicalRecordResponseDTO createMedicalRecord(MedicalRecordRequestDTO request) {
        Patient patient = patientRepository.findById(request.patientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Doctor doctor = doctorRepository.findById(request.doctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        MedicalRecord medicalRecord = MedicalRecord.builder()
                .patient(patient)
                .doctor(doctor)
                .diagnosis(request.diagnosis())
                .treatment(request.treatment())
                .build();

        return new MedicalRecordResponseDTO(medicalRecordRepository.save(medicalRecord));
    }

    public List<MedicalRecordResponseDTO> getAllMedicalRecords() {
        return medicalRecordRepository.findAll()
                .stream()
                .map(MedicalRecordResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<MedicalRecordResponseDTO> getMedicalRecordsByPatientId(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId)
                .stream()
                .map(MedicalRecordResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<MedicalRecordResponseDTO> getMedicalRecordsByDoctorId(Long doctorId) {
        return medicalRecordRepository.findByDoctorId(doctorId)
                .stream()
                .map(MedicalRecordResponseDTO::new)
                .collect(Collectors.toList());
    }
}
