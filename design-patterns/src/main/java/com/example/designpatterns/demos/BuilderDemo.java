package main.java.com.example.designpatterns.demos;

import main.java.com.example.designpatterns.patterns.builder.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class BuilderDemo {
    private static final Logger logger = LoggerFactory.getLogger(BuilderDemo.class);

    public static void runDemo() {
        logger.info("Running Builder Pattern demo: Building complex reports");

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Builder Pattern Demo ===");
        System.out.println("Let's build a custom report step by step.");

        // Get report title
        System.out.print("Enter report title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            title = "Monthly Sales Report";
            System.out.println("Using default title: " + title);
        }

        // Get author
        System.out.print("Enter author name: ");
        String author = scanner.nextLine().trim();
        if (author.isEmpty()) {
            author = "Anonymous";
            System.out.println("Using default author: " + author);
        }

        Report.Builder builder = new Report.Builder(title).withAuthor(author);

        // Add sections
        boolean addingSections = true;
        while (addingSections) {
            System.out.print("Enter section title (or 'done' to finish): ");
            String sectionTitle = scanner.nextLine().trim();
            if ("done".equalsIgnoreCase(sectionTitle)) {
                addingSections = false;
            } else {
                System.out.print("Enter section content: ");
                String content = scanner.nextLine().trim();
                builder.addSection(sectionTitle, content);
                System.out.println("Section added.");
            }
        }

        // Enable footer?
        System.out.print("Enable footer? (y/n): ");
        String footerChoice = scanner.nextLine().trim().toLowerCase();
        if ("y".equals(footerChoice) || "yes".equals(footerChoice)) {
            builder.enableFooter(true);
        }

        Report report = builder.build();
        System.out.println("\nReport built successfully!");
        System.out.println(report);
    }
}
