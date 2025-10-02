package main.java.com.example.designpatterns.patterns.adapter;

public class ThirdPartyPaymentProcessor {
    // This is an incompatible API we need to adapt to
    public boolean makePaymentInCents(long amountCents, String cardNumber, int cvv, String expiry) throws Exception {
        if (cardNumber == null || cardNumber.length() < 12) throw new IllegalArgumentException("invalid card");
        // Simulate transient failure for demo
        if (Math.random() < 0.3) throw new RuntimeException("Temporary gateway failure");
        System.out.printf("ThirdParty paid %d cents using card %s\n", amountCents, cardNumber);
        return true;
    }
}
