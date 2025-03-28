package com.icbt.hospitalsystem.billing;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/billing")
@RequiredArgsConstructor
public class BillingController {

    private final BillingService billingService;

    @PostMapping
    public ResponseEntity<BillingResponseDTO> createBill(@RequestParam Long patientId, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(billingService.createBill(patientId, amount));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<BillingResponseDTO>> getBillsByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(billingService.getBillsByPatientId(patientId));
    }

    @GetMapping
    public ResponseEntity<List<BillingResponseDTO>> getAllBills() {
        return ResponseEntity.ok(billingService.getAllBills());
    }

    @PatchMapping("/{billId}/update-status")
    public ResponseEntity<BillingResponseDTO> updatePaymentStatus(@PathVariable Long billId, @RequestParam PaymentStatusEnum status) {
        return ResponseEntity.ok(billingService.updatePaymentStatus(billId, status));
    }
}
