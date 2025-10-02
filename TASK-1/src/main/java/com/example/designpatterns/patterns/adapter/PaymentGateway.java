package main.java.com.example.designpatterns.patterns.adapter;

public interface PaymentGateway {
    void pay(PaymentRequest req) throws Exception;
}
