package com.icbt.hospitalsystem.authentication;

import com.icbt.hospitalsystem.user.UserRequestDTO;
import com.icbt.hospitalsystem.user.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(authenticationService.register(userRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(loginRequest));
    }
}
