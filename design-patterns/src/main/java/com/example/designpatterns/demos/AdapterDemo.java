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
        String amountStr = scanner.nextLine().trim();
        double amount = amountStr.isEmpty() ? 100.0 : Double.parseDouble(amountStr);

        System.out.print("Enter card number (or press Enter for default): ");
        String cardNumber = scanner.nextLine().trim();
        if (cardNumber.isEmpty()) {
            cardNumber = "4111111111111111"; // Default test card number (16 digits)
            System.out.println("Using default card number: " + cardNumber);
        } else if (cardNumber.length() < 12) {
            System.out.println("Card number too short, using default: 4111111111111111");
            cardNumber = "4111111111111111";
        }

        PaymentRequest request = new PaymentRequest(cardNumber, amount);

        // Choose gateway
        System.out.println("Choose payment gateway:");
        System.out.println("1 - Standard Gateway");
        System.out.println("2 - Third-Party Gateway (via Adapter)");
        System.out.print("Enter choice: ");
        String choiceStr = scanner.nextLine().trim();
        int choice = choiceStr.isEmpty() ? 2 : Integer.parseInt(choiceStr);

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
