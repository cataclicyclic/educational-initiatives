package main.java.com.example.designpatterns.demos;

import main.java.com.example.designpatterns.patterns.observer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ObserverDemo {
    private static final Logger logger = LoggerFactory.getLogger(ObserverDemo.class);

    public static void runDemo() {
        logger.info("Running Observer Pattern demo: Order notifications");

        Scanner scanner = new Scanner(System.in);
        Order order = new Order("ORD-1001");

        System.out.println("=== Observer Pattern Demo ===");
        System.out.println("Let's create an order and register observers to watch for status changes.");

        // Register observers interactively
        System.out.println("Available observers:");
        System.out.println("1 - Email Notifier");
        System.out.println("2 - Audit Logger");
        System.out.println("3 - Both");
        System.out.print("Choose observers to register: ");
        int choice = Integer.parseInt(scanner.nextLine().trim());

        switch (choice) {
            case 1:
                System.out.print("Enter email address: ");
                String email = scanner.nextLine().trim();
                order.registerObserver(new EmailNotifier(email));
                System.out.println("Email notifier registered.");
                break;
            case 2:
                order.registerObserver(new AuditLogger());
                System.out.println("Audit logger registered.");
                break;
            case 3:
                System.out.print("Enter email address: ");
                String emailBoth = scanner.nextLine().trim();
                order.registerObserver(new EmailNotifier(emailBoth));
                order.registerObserver(new AuditLogger());
                System.out.println("Both observers registered.");
                break;
            default:
                System.out.println("Invalid choice, using default: Audit Logger.");
                order.registerObserver(new AuditLogger());
        }

        // Change status interactively
        System.out.println("\nNow let's change the order status:");
        System.out.println("Available statuses:");
        System.out.println("1 - PROCESSING");
        System.out.println("2 - SHIPPED");
        System.out.println("3 - DELIVERED");
        System.out.print("Choose new status: ");
        int statusChoice = Integer.parseInt(scanner.nextLine().trim());

        Order.Status newStatus;
        switch (statusChoice) {
            case 1:
                newStatus = Order.Status.PROCESSING;
                break;
            case 2:
                newStatus = Order.Status.SHIPPED;
                break;
            case 3:
                newStatus = Order.Status.DELIVERED;
                break;
            default:
                System.out.println("Invalid choice, using PROCESSING.");
                newStatus = Order.Status.PROCESSING;
        }

        System.out.println("Changing order status to " + newStatus + "...");
        order.setStatus(newStatus);
        System.out.println("Status change complete. Observers have been notified.");
    }
}
