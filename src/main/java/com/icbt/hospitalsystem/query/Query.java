package com.icbt.hospitalsystem.query;

import com.icbt.hospitalsystem.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Query {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private UserEntity patient;

    private String subject;
    private String message;

    private boolean answered;

    private LocalDateTime createdAt;
    private LocalDateTime answeredAt;

    @ManyToOne
    @JoinColumn(name = "answered_by")
    private UserEntity answeredBy;
}
