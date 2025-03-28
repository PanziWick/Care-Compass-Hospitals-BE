package com.icbt.hospitalsystem.billing;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BillingResponseDTO(
        Long id,
        Long patientId,
        BigDecimal totalAmount,
        PaymentStatusEnum status,
        LocalDateTime billingDate
) {
    public BillingResponseDTO(Billing billing) {
        this(
                billing.getId(),
                billing.getPatient().getId(),
                billing.getTotalAmount(),
                billing.getStatus(),
                billing.getBillingDate()
        );
    }
}
