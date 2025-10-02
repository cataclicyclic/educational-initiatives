package main.java.com.example.designpatterns.demos;

import main.java.com.example.designpatterns.patterns.builder.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuilderDemo {
    private static final Logger logger = LoggerFactory.getLogger(BuilderDemo.class);

    public static void runDemo() {
        logger.info("Running Builder Pattern demo: Building complex reports");

        Report rpt = new Report.Builder("Monthly Sales")
                .withAuthor("Ishan")
                .addSection("Summary", "All good")
                .addSection("Details", "Lots of numbers")
                .enableFooter(true)
                .build();

        System.out.println(rpt);
    }
}
