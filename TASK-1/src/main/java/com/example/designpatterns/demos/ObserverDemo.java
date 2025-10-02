package main.java.com.example.designpatterns.demos;

import main.java.com.example.designpatterns.patterns.observer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObserverDemo {
    private static final Logger logger = LoggerFactory.getLogger(ObserverDemo.class);

    public static void runDemo() {
        logger.info("Running Observer Pattern demo: Order notifications");

        Order order = new Order("ORD-1001");
        order.registerObserver(new EmailNotifier("ops@example.com"));
        order.registerObserver(new AuditLogger());

        order.setStatus(Order.Status.PROCESSING);
        order.setStatus(Order.Status.SHIPPED);
    }
}
