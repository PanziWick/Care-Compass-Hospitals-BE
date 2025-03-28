package com.icbt.hospitalsystem.user;

import com.icbt.hospitalsystem.common.ResourceNotFoundException;
import com.icbt.hospitalsystem.doctor.Doctor;
import com.icbt.hospitalsystem.doctor.DoctorRepository;
import com.icbt.hospitalsystem.patient.Patient;
import com.icbt.hospitalsystem.patient.PatientRepository;
import com.icbt.hospitalsystem.staff.Staff;
import com.icbt.hospitalsystem.staff.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final StaffRepository staffRepository;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String USER_NOT_FOUND = "User not found";

    public UserResponseDTO createUser(UserRequestDTO userRequest) {
        UserEntity user = new UserEntity();
        user.setUsername(userRequest.username());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setRole(userRequest.role());

        UserEntity savedUser = userRepository.save(user);

        if (userRequest.role() == UserRoleEnum.PATIENT) {
            Patient patient = new Patient();
            patient.setUser(savedUser);
            patient.setMedicalHistory("New Patient");
            patientRepository.save(patient);
        }

        if (userRequest.role() == UserRoleEnum.STAFF || userRequest.role() == UserRoleEnum.DOCTOR) {
            Staff staff = new Staff();
            staff.setUser(savedUser);
            staff.setDepartment("General");
            staff.setJobTitle(userRequest.role() == UserRoleEnum.DOCTOR ? "Doctor" : "Staff");
            Staff savedStaff = staffRepository.save(staff);

            if (userRequest.role() == UserRoleEnum.DOCTOR) {
                Doctor doctor = new Doctor();
                doctor.setUser(user);
                doctor.setStaff(savedStaff);
                doctor.setSpecialization("General Medicine");
                doctorRepository.save(doctor);
            }
        }

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<UserResponseDTO> getAllDoctors() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRole() == UserRoleEnum.DOCTOR)
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<UserResponseDTO> getAllPatients() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRole() == UserRoleEnum.PATIENT)
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequest) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));
        user.setUsername(userRequest.username());
        user.setPassword(passwordEncoder.encode(userRequest.password()));

        userRepository.save(user);

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public RoleUpdateDTO updateUserRole(Long id, RoleUpdateDTO role) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));
        user.setRole(role.role());

        userRepository.save(user);

        return new RoleUpdateDTO(
                user.getRole()
        );
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));
    }
}
