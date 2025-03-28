package com.icbt.hospitalsystem.authentication;

import com.icbt.hospitalsystem.user.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private UserResponseDTO user;
    private String token;
}
