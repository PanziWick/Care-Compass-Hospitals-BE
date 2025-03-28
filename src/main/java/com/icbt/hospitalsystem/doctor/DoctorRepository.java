package com.icbt.hospitalsystem.doctor;

import com.icbt.hospitalsystem.patient.Patient;
import com.icbt.hospitalsystem.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    void deleteByUser(UserEntity user);

    @Query("SELECT d FROM Doctor d WHERE d.user.id = :userId")
    Optional<Doctor> findByUserId(@Param("userId") Long userId);
}
