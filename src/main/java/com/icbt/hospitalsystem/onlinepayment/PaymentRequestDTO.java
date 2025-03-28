package com.icbt.hospitalsystem.onlinepayment;

public record PaymentRequestDTO(
        String currency,
        Long amount,
        String token
) {
}
