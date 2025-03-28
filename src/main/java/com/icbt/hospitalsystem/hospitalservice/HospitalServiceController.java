package com.icbt.hospitalsystem.hospitalservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospital-services")
@RequiredArgsConstructor
public class HospitalServiceController {
    private final HospitalServiceService hospitalServiceService;

    @PostMapping
    public ResponseEntity<HospitalServiceResponseDTO> createService(@RequestBody HospitalServiceRequestDTO request) {
        return ResponseEntity.ok(hospitalServiceService.createService(request));
    }

    @GetMapping
    public ResponseEntity<List<HospitalServiceResponseDTO>> getAllServices() {
        return ResponseEntity.ok(hospitalServiceService.getAllServices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalServiceResponseDTO> getServiceById(@PathVariable Long id) {
        return ResponseEntity.ok(hospitalServiceService.getServiceById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HospitalServiceResponseDTO> updateService(@PathVariable Long id, @RequestBody HospitalServiceRequestDTO request) {
        return ResponseEntity.ok(hospitalServiceService.updateService(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Long id) {
        hospitalServiceService.deleteService(id);
        return ResponseEntity.ok("Service deleted successfully.");
    }
}
