package com.icbt.hospitalsystem.user;

public record UserRequestDTO(
        String username,
        String password,
        UserRoleEnum role,
        String designation
) {
}