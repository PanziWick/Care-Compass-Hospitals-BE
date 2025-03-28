package com.icbt.hospitalsystem.user;

public record UserResponseDTO(
        Long id,
        String username,
        UserRoleEnum role
) {
    public UserResponseDTO(UserEntity user) {
        this(user.getId(), user.getUsername(), user.getRole());
    }
}