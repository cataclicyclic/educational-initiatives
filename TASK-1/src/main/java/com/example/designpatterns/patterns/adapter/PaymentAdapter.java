package main.java.com.example.designpatterns.patterns.adapter;

import main.java.com.example.designpatterns.RetryPolicy;

public class PaymentAdapter implements PaymentGateway {
    private final ThirdPartyPaymentProcessor backend;
    private final RetryPolicy retry = new RetryPolicy(3, 200);

    public PaymentAdapter(ThirdPartyPaymentProcessor backend) {
        this.backend = backend;
    }

    @Override
    public void pay(PaymentRequest req) throws Exception {
        if (req == null) throw new IllegalArgumentException("request required");
        long cents = Math.round(req.amount * 100);
        // adapt and use retry policy for transient errors
        retry.execute(() -> {
            backend.makePaymentInCents(cents, req.cardNumber, 123, "12/27");
            return null;
        });
    }
}
