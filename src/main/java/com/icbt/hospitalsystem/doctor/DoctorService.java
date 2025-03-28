package com.icbt.hospitalsystem.doctor;

import com.icbt.hospitalsystem.common.ResourceNotFoundException;
import com.icbt.hospitalsystem.user.UserEntity;
import com.icbt.hospitalsystem.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    private static final String DOCTOR_NOT_FOUND = "Doctor not found";

    public List<DoctorResponseDTO> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(DoctorResponseDTO::new)
                .collect(Collectors.toList());
    }

    public DoctorResponseDTO getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(DOCTOR_NOT_FOUND));

        return new DoctorResponseDTO(
                doctor.getId(),
                doctor.getUser().getUsername(),
                doctor.getSpecialization()
        );
    }

    public DoctorResponseDTO getDoctorByUserId(Long userId) {
        Doctor doctor = doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException(DOCTOR_NOT_FOUND));

        return new DoctorResponseDTO(
                doctor.getId(),
                doctor.getUser().getUsername(),
                doctor.getSpecialization()
        );
    }
}
