package com.icbt.hospitalsystem.onlinepayment;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final StripeService stripeService;

    @PostMapping("/charge")
    public ResponseEntity<?> charge(@RequestBody PaymentRequestDTO request) {
        try {
            Charge charge = stripeService.charge(request);
            return ResponseEntity.ok(charge);
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body("Payment failed: " + e.getMessage());
        }
    }
}
