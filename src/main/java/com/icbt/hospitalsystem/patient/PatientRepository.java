package com.icbt.hospitalsystem.patient;

import com.icbt.hospitalsystem.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    void deleteByUser(UserEntity user);

    @Query("SELECT p FROM Patient p WHERE p.user.id = :userId")
    Optional<Patient> findByUserId(@Param("userId") Long userId);
}
