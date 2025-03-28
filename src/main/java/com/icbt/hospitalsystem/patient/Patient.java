package com.icbt.hospitalsystem.patient;

import com.icbt.hospitalsystem.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private GenderEnum genderEnum;

    @Column(columnDefinition = "TEXT")
    private String medicalHistory;
}
