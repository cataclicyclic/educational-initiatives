package main.java.com.example.designpatterns.demos;

import main.java.com.example.designpatterns.patterns.adapter.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class AdapterDemo {
    private static final Logger logger = LoggerFactory.getLogger(AdapterDemo.class);

    public static void runDemo() {
        logger.info("Running Adapter Pattern demo: Payment gateway adapter");

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Adapter Pattern Demo ===");
        System.out.println("Let's make a payment using the adapter to connect to a third-party processor.");

        // Get payment details
        System.out.print("Enter payment amount: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Enter card number: ");
        String cardNumber = scanner.nextLine().trim();

        PaymentRequest request = new PaymentRequest(cardNumber, amount);

        // Choose gateway
        System.out.println("Choose payment gateway:");
        System.out.println("1 - Standard Gateway");
        System.out.println("2 - Third-Party Gateway (via Adapter)");
        System.out.print("Enter choice: ");
        int choice = Integer.parseInt(scanner.nextLine().trim());

        PaymentGateway gateway;
        if (choice == 2) {
            gateway = new PaymentAdapter(new ThirdPartyPaymentProcessor());
            System.out.println("Using Third-Party Gateway via Adapter...");
        } else {
            // Assuming there's a standard gateway, but for demo, use adapter
            gateway = new PaymentAdapter(new ThirdPartyPaymentProcessor());
            System.out.println("Using Standard Gateway...");
        }

        try {
            gateway.pay(request);
            System.out.println("Payment processed successfully!");
        } catch (Exception e) {
            logger.error("Adapter demo failed", e);
            System.out.println("Error processing payment: " + e.getMessage());
        }
    }
}
