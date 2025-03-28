package com.icbt.hospitalsystem.onlinepayment;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    public Charge charge(PaymentRequestDTO request) throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", request.amount()); // Amount in cents
        chargeParams.put("currency", request.currency());
        chargeParams.put("source", request.token()); // Stripe token from frontend

        return Charge.create(chargeParams);
    }
}
