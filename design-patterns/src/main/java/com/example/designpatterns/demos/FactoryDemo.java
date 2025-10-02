package main.java.com.example.designpatterns.demos;

import main.java.com.example.designpatterns.patterns.factory.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FactoryDemo {
    private static final Logger logger = LoggerFactory.getLogger(FactoryDemo.class);

    public static void runDemo() {
        logger.info("Running Factory Method demo: Document parsers");

        ParserFactory factory = new ParserFactory();
        try {
            DocumentParser p1 = factory.createParser("json");
            p1.parse("{ \"name\": \"Ishan\" }");

            DocumentParser p2 = factory.createParser("xml");
            p2.parse("<root><name>Ishan</name></root>");

        } catch (Exception e) {
            logger.error("Factory demo failed", e);
        }
    }
}
