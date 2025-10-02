package main.java.com.example.designpatterns.demos;

import main.java.com.example.designpatterns.patterns.factory.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class FactoryDemo {
    private static final Logger logger = LoggerFactory.getLogger(FactoryDemo.class);

    public static void runDemo() {
        logger.info("Running Factory Method demo: Document parsers");

        Scanner scanner = new Scanner(System.in);
        ParserFactory factory = new ParserFactory();

        System.out.println("=== Factory Method Pattern Demo ===");
        System.out.println("Let's parse different document types using the factory.");

        // Choose parser type
        System.out.println("Available parser types:");
        System.out.println("1 - JSON");
        System.out.println("2 - XML");
        System.out.print("Choose parser type: ");
        int choice = Integer.parseInt(scanner.nextLine().trim());

        String type;
        switch (choice) {
            case 1:
                type = "json";
                break;
            case 2:
                type = "xml";
                break;
            default:
                System.out.println("Invalid choice, using JSON.");
                type = "json";
        }

        // Input document content
        System.out.println("Enter document content to parse:");
        String content = scanner.nextLine().trim();

        if (content.isEmpty()) {
            // Use sample content
            if ("json".equals(type)) {
                content = "{ \"name\": \"John Doe\", \"age\": 30 }";
            } else {
                content = "<person><name>John Doe</name><age>30</age></person>";
            }
            System.out.println("Using sample content: " + content);
        }

        try {
            DocumentParser parser = factory.createParser(type);
            System.out.println("Parsing with " + type.toUpperCase() + " parser...");
            parser.parse(content);
            System.out.println("Parsing complete!");
        } catch (Exception e) {
            logger.error("Factory demo failed", e);
            System.out.println("Error during parsing: " + e.getMessage());
        }
    }
}
