package main.java.com.example.designpatterns.demos;

import main.java.com.example.designpatterns.patterns.strategy.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class StrategyDemo {
    private static final Logger logger = LoggerFactory.getLogger(StrategyDemo.class);

    public static void runDemo() {
        logger.info("Running Strategy Pattern demo: Discount strategies");

        Cart cart = new Cart();
        cart.addItem(new CartItem("Book", 2, 300));
        cart.addItem(new CartItem("Pen", 5, 20));

        DiscountStrategy no = new NoDiscountStrategy();
        DiscountStrategy perc = new PercentageDiscountStrategy(0.1);
        DiscountStrategy bulk = new BulkDiscountStrategy(3, 0.15);

        System.out.println("Base total: " + cart.total());
        Arrays.asList(no, perc, bulk).forEach(s -> {
            double after = s.applyDiscount(cart);
            System.out.printf("Strategy %s -> total after discount: %.2f\n", s.getClass().getSimpleName(), after);
        });
    }
}
