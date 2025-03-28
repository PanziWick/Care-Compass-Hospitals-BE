package com.icbt.hospitalsystem.user;

import com.icbt.hospitalsystem.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<UserResponseDTO>> getAllDoctors() {
        return ResponseEntity.ok(userService.getAllDoctors());
    }

    @GetMapping("/patients")
    public ResponseEntity<List<UserResponseDTO>> getAllPatients() {
        return ResponseEntity.ok(userService.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequest) {
        return ResponseEntity.ok(userService.updateUser(id, userRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully.");
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<RoleUpdateDTO> updateUserRole(@PathVariable Long id, @RequestBody RoleUpdateDTO roleUpdate) {
        return ResponseEntity.ok(userService.updateUserRole(id, roleUpdate));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        String username = jwtService.extractUsername(jwt);
        UserEntity user = userService.findByUsername(username);
        return ResponseEntity.ok(new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getRole()
                )
        );
    }

}