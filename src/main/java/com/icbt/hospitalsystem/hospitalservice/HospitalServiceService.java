package com.icbt.hospitalsystem.hospitalservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalServiceService {
    private final HospitalServiceRepository hospitalServiceRepository;

    public HospitalServiceResponseDTO createService(HospitalServiceRequestDTO requestDTO) {
        HospitalServiceEntity hospitalService = new HospitalServiceEntity();
        hospitalService.setServiceName(requestDTO.serviceName());
        hospitalService.setDescription(requestDTO.description());
        hospitalService.setPrice(requestDTO.price());

        // Save to DB first
        HospitalServiceEntity savedService = hospitalServiceRepository.save(hospitalService);

        // Now return response with correct ID
        return new HospitalServiceResponseDTO(
                savedService.getId(),
                savedService.getServiceName(),
                savedService.getDescription(),
                savedService.getPrice()
        );
    }
    
    public List<HospitalServiceResponseDTO> getAllServices() {
        return hospitalServiceRepository.findAll().stream().map(HospitalServiceResponseDTO::new).toList();
    }

    public HospitalServiceResponseDTO getServiceById(Long id) {
        return hospitalServiceRepository.findById(id).map(HospitalServiceResponseDTO::new)
                .orElseThrow(() -> new RuntimeException("Service not found"));
    }

    public HospitalServiceResponseDTO updateService(Long id, HospitalServiceRequestDTO request) {
        HospitalServiceEntity service = hospitalServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        service.setServiceName(request.serviceName());
        service.setDescription(request.description());
        service.setPrice(request.price());

        HospitalServiceEntity savedService = hospitalServiceRepository.save(service);

        return new HospitalServiceResponseDTO(
                savedService.getId(),
                savedService.getServiceName(),
                savedService.getDescription(),
                savedService.getPrice()
        );
    }

    public void deleteService(Long id) {
        hospitalServiceRepository.deleteById(id);
    }
}
