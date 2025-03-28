package com.icbt.hospitalsystem.staff;

import com.icbt.hospitalsystem.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Staff")
@Data
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

    private String department;
    private String jobTitle;
    private String designation;
}
