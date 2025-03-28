package com.icbt.hospitalsystem.query;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueryRepository extends JpaRepository<Query, Long> {
    List<Query> findByPatientId(Long patientId);
    List<Query> findByAnswered(boolean answered);
}
