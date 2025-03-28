package com.icbt.hospitalsystem.authentication;

import com.icbt.hospitalsystem.security.JwtService;
import com.icbt.hospitalsystem.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public UserResponseDTO register(UserRequestDTO userRequestDTO) {
        try {
            return userService.createUser(userRequestDTO);
        } catch (Exception e) {
            throw new RuntimeException("User registration failed", e);
        }
    }

    public LoginResponseDTO authenticate(LoginRequestDTO loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException("Invalid username or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        UserEntity user = userService.findByUsername(loginRequest.getUsername());
        String token = jwtService.generateToken(userDetails);

        return new LoginResponseDTO(
                new UserResponseDTO(user),
                token
        );
    }
}
