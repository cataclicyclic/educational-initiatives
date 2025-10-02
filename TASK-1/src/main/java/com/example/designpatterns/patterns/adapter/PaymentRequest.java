package main.java.com.example.designpatterns.patterns.adapter;

public class PaymentRequest {
    public final String cardNumber;
    public final double amount;

    public PaymentRequest(String cardNumber, double amount) {
        this.cardNumber = cardNumber;
        this.amount = amount;
    }
}
