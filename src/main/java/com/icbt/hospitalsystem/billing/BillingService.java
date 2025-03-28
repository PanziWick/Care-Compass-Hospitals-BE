package com.icbt.hospitalsystem.billing;

import com.icbt.hospitalsystem.common.ResourceNotFoundException;
import com.icbt.hospitalsystem.patient.Patient;
import com.icbt.hospitalsystem.patient.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillingService {

    private final BillingRepository billingRepository;
    private final PatientRepository patientRepository;

    public BillingResponseDTO createBill(Long patientId, BigDecimal totalAmount) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        Billing bill = Billing.builder()
                .patient(patient)
                .totalAmount(totalAmount)
                .status(PaymentStatusEnum.PENDING)
                .billingDate(LocalDateTime.now())
                .build();

        Billing savedBill = billingRepository.save(bill);
        return new BillingResponseDTO(savedBill);
    }

    public List<BillingResponseDTO> getBillsByPatientId(Long patientId) {
        return billingRepository.findByPatientId(patientId)
                .stream()
                .map(BillingResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<BillingResponseDTO> getAllBills() {
        return billingRepository.findAll()
                .stream()
                .map(BillingResponseDTO::new)
                .collect(Collectors.toList());
    }

    public BillingResponseDTO updatePaymentStatus(Long billId, PaymentStatusEnum status) {
        Billing bill = billingRepository.findById(billId)
                .orElseThrow(() -> new ResourceNotFoundException("Billing record not found"));

        bill.setStatus(status);
        billingRepository.save(bill);
        return new BillingResponseDTO(bill);
    }
}

