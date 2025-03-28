package com.icbt.hospitalsystem.appoinment;

import com.icbt.hospitalsystem.common.ResourceNotFoundException;
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
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    private static final String APPOINTMENT_NOT_FOUND = "Appointment not found";

    public AppointmentResponseDTO bookAppointment(AppointmentRequestDTO request) {
        Patient patient = patientRepository.findById(request.patientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        Doctor doctor = doctorRepository.findById(request.doctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        Appointment appointment = Appointment.builder()
                .patient(patient)
                .doctor(doctor)
                .appointmentDate(request.appointmentDate())
                .status(AppointmentStatusEnum.PENDING)
                .build();

        Appointment savedAppointment = appointmentRepository.save(appointment);
        return new AppointmentResponseDTO(savedAppointment);
    }

    public List<AppointmentResponseDTO> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(AppointmentResponseDTO::new)
                .collect(Collectors.toList());
    }

    public AppointmentResponseDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(APPOINTMENT_NOT_FOUND));
        return new AppointmentResponseDTO(appointment);
    }

    public List<AppointmentResponseDTO> getPatientAppointmentsByUserId(Long userId) {

        long patientId = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"))
                .getId();

        return appointmentRepository.findByPatientId(patientId)
                .stream()
                .map(AppointmentResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<AppointmentResponseDTO> getDoctorAppointmentsByUserId(Long userId) {

        long doctorId = doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"))
                .getId();

        return appointmentRepository.findByDoctorId(doctorId)
                .stream()
                .map(AppointmentResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<AppointmentResponseDTO> getAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId)
                .stream()
                .map(AppointmentResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<AppointmentResponseDTO> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId)
                .stream()
                .map(AppointmentResponseDTO::new)
                .collect(Collectors.toList());
    }


    public AppointmentResponseDTO updateAppointmentStatus(Long id, AppointmentStatusEnum status) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(APPOINTMENT_NOT_FOUND));
        appointment.setStatus(status);
        return new AppointmentResponseDTO(appointmentRepository.save(appointment));
    }

    public void cancelAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ResourceNotFoundException(APPOINTMENT_NOT_FOUND);
        }
        appointmentRepository.deleteById(id);
    }
}


