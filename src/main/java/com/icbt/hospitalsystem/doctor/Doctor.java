package com.icbt.hospitalsystem.doctor;

import com.icbt.hospitalsystem.staff.Staff;
import com.icbt.hospitalsystem.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "doctors")
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "staff_id", nullable = false, unique = true)
    private Staff staff;

    private String specialization;
}
