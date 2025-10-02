package main.java.com.example.designpatterns.demos;

import main.java.com.example.designpatterns.patterns.decorator.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DecoratorDemo {
    private static final Logger logger = LoggerFactory.getLogger(DecoratorDemo.class);

    public static void runDemo() {
        logger.info("Running Decorator Pattern demo: Message decorators");

        MessageService service = new SimpleMessageService();
        service = new CompressionDecorator(service);
        service = new EncryptionDecorator(service);

        service.send("Hello Patterns!");
    }
}
