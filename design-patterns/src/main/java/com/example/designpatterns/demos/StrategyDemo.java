package main.java.com.example.designpatterns.demos;

import main.java.com.example.designpatterns.patterns.strategy.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class StrategyDemo {
    private static final Logger logger = LoggerFactory.getLogger(StrategyDemo.class);

    public static void runDemo() {
        logger.info("Running Strategy Pattern demo: Discount strategies");

        Scanner scanner = new Scanner(System.in);
        Cart cart = new Cart();

        System.out.println("=== Strategy Pattern Demo ===");
        System.out.println("Let's build a shopping cart and apply discount strategies.");

        // Add items interactively
        boolean adding = true;
        while (adding) {
            System.out.print("Enter item name (or 'done' to finish): ");
            String name = scanner.nextLine().trim();
            if ("done".equalsIgnoreCase(name)) {
                adding = false;
            } else {
                System.out.print("Enter quantity: ");
                int qty = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("Enter price per unit: ");
                double price = Double.parseDouble(scanner.nextLine().trim());
                cart.addItem(new CartItem(name, qty, price));
                System.out.println("Added: " + name + " x" + qty + " @ $" + price);
            }
        }

        if (cart.itemsCount() == 0) {
            System.out.println("No items in cart. Using sample cart.");
            cart.addItem(new CartItem("Book", 2, 300));
            cart.addItem(new CartItem("Pen", 5, 20));
        }

        System.out.println("\nCart contents:");
        // Note: Cart doesn't expose items, so just show total
        System.out.println("Total items: " + cart.itemsCount());
        System.out.printf("Base total: $%.2f\n", cart.total());

        // Choose strategy
        System.out.println("\nChoose a discount strategy:");
        System.out.println("1 - No discount");
        System.out.println("2 - Percentage discount (10%)");
        System.out.println("3 - Bulk discount (15% if 3+ items)");
        System.out.print("Enter choice: ");
        int choice = Integer.parseInt(scanner.nextLine().trim());

        DiscountStrategy strategy;
        switch (choice) {
            case 1:
                strategy = new NoDiscountStrategy();
                break;
            case 2:
                strategy = new PercentageDiscountStrategy(0.1);
                break;
            case 3:
                strategy = new BulkDiscountStrategy(3, 0.15);
                break;
            default:
                System.out.println("Invalid choice, using no discount.");
                strategy = new NoDiscountStrategy();
        }

        double discounted = strategy.applyDiscount(cart);
        System.out.printf("After discount: $%.2f\n", discounted);
        System.out.println("Strategy used: " + strategy.getClass().getSimpleName());
    }
}
