package com.icbt.hospitalsystem.feedback;

import com.icbt.hospitalsystem.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private UserEntity patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private UserEntity doctor;

    private String comment;
    private int rating; // 1 to 5 stars

    private LocalDateTime createdAt;
}
