package com.icbt.hospitalsystem.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public List<DoctorResponseDTO> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public DoctorResponseDTO getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @GetMapping("/user/{userId}")
    public DoctorResponseDTO getDoctorByUserId(@PathVariable Long userId) {
        return doctorService.getDoctorByUserId(userId);
    }
}
