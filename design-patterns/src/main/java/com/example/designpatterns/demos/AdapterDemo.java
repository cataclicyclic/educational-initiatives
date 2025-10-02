package main.java.com.example.designpatterns.demos;

import main.java.com.example.designpatterns.patterns.adapter.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdapterDemo {
    private static final Logger logger = LoggerFactory.getLogger(AdapterDemo.class);

    public static void runDemo() {
        logger.info("Running Adapter Pattern demo: Payment gateway adapter");

        ThirdPartyPaymentProcessor thirdParty = new ThirdPartyPaymentProcessor();
        PaymentGateway gateway = new PaymentAdapter(thirdParty);

        try {
            gateway.pay(new PaymentRequest("4111111111111111", 49.99));
        } catch (Exception e) {
            logger.error("Payment failed", e);
        }
    }
}
