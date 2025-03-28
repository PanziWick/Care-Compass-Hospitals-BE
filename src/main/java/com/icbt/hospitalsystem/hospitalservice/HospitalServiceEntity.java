package com.icbt.hospitalsystem.hospitalservice;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "hospital_services")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String serviceName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal price;
}
