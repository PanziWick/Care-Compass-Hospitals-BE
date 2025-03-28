package com.icbt.hospitalsystem.staff;

import com.icbt.hospitalsystem.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    void deleteByUser(UserEntity user);
}
