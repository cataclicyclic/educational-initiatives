package main.java.com.example.designpatterns;

import main.java.com.example.designpatterns.demos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.concurrent.*;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Starting Design Patterns Demo App");

        AppConfig config = AppConfig.load();

        BlockingQueue<String> commandQueue = new LinkedBlockingQueue<>();

        // Scheduled background telemetry (no while(true))
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> logger.debug("Heartbeat - app alive"),
                0, config.getHeartbeatSeconds(), TimeUnit.SECONDS);

        // Input reader thread (blocking on console input)
        ExecutorService inputReader = Executors.newSingleThreadExecutor();
        inputReader.submit(() -> {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                String line;
                printMenu();
                while ((line = br.readLine()) != null) {
                    String trimmed = line.trim();
                    if (!trimmed.isEmpty()) {
                        commandQueue.put(trimmed);
                        if ("exit".equalsIgnoreCase(trimmed)) {
                            break; // stop reading more input
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("Input reader failed", e);
            }
        });

        // Menu processor: consumes commands and executes demos
        ExecutorService menuProcessor = Executors.newSingleThreadExecutor();
        menuProcessor.submit(() -> {
            try {
                boolean running = true;
                while (running) {
                    String cmd = commandQueue.take(); // blocks until a command is available
                    switch (cmd.toLowerCase()) {
                        case "1":
                            StrategyDemo.runDemo();
                            break;
                        case "2":
                            ObserverDemo.runDemo();
                            break;
                        case "3":
                            FactoryDemo.runDemo();
                            break;
                        case "4":
                            BuilderDemo.runDemo();
                            break;
                        case "5":
                            AdapterDemo.runDemo();
                            break;
                        case "6":
                            DecoratorDemo.runDemo();
                            break;
                        case "menu":
                            printMenu();
                            break;
                        case "help":
                            printHelp();
                            break;
                        case "exit":
                            logger.info("Exit requested. Shutting down...");
                            running = false;
                            break;
                        default:
                            if (cmd.startsWith("help ")) {
                                String pattern = cmd.substring(5).trim();
                                printPatternHelp(pattern);
                            } else {
                                System.out.println("Unknown command: " + cmd + ". Type 'menu' to show options, 'help' for general help, or 'exit' to quit.");
                            }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.warn("Menu processor interrupted", e);
            } catch (Exception e) {
                logger.error("Menu processor error", e);
            } finally {
                shutdownGracefully(scheduler, inputReader, menuProcessor);
            }
        });

        // Wait for menuProcessor to finish
        try {
            menuProcessor.shutdown();
            menuProcessor.awaitTermination(Duration.ofHours(1).toMillis(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        logger.info("Application stopped");
    }

    private static void printMenu() {
        System.out.println("=== Design Patterns Demo ===");
        System.out.println("1 - Strategy Pattern (Discount strategies)");
        System.out.println("2 - Observer Pattern (Order notifications)");
        System.out.println("3 - Factory Method (Document parsers)");
        System.out.println("4 - Builder Pattern (Complex Report builder)");
        System.out.println("5 - Adapter Pattern (Payment gateway adapter)");
        System.out.println("6 - Decorator Pattern (Message decorators)");
        System.out.println("menu - show this menu");
        System.out.println("help - general help");
        System.out.println("help <number> - help for specific pattern");
        System.out.println("exit - stop the app");
        System.out.println("Enter choice: ");
    }

    private static void printHelp() {
        System.out.println("=== General Help ===");
        System.out.println("This app demonstrates six common design patterns in Java.");
        System.out.println("Each pattern solves a specific problem in software design.");
        System.out.println("Select a number (1-6) to run an interactive demo.");
        System.out.println("Use 'help <number>' for details on a specific pattern.");
        System.out.println("Type 'menu' to see options again.");
        System.out.println("Type 'exit' to quit.");
    }

    private static void printPatternHelp(String pattern) {
        switch (pattern) {
            case "1":
                System.out.println("=== Strategy Pattern Help ===");
                System.out.println("Allows selecting an algorithm at runtime.");
                System.out.println("In this demo: Choose discount strategies for a shopping cart.");
                break;
            case "2":
                System.out.println("=== Observer Pattern Help ===");
                System.out.println("Notifies multiple objects about state changes.");
                System.out.println("In this demo: Order status changes notify observers.");
                break;
            case "3":
                System.out.println("=== Factory Method Help ===");
                System.out.println("Creates objects without specifying exact classes.");
                System.out.println("In this demo: Parse different document types.");
                break;
            case "4":
                System.out.println("=== Builder Pattern Help ===");
                System.out.println("Constructs complex objects step by step.");
                System.out.println("In this demo: Build a report with optional parts.");
                break;
            case "5":
                System.out.println("=== Adapter Pattern Help ===");
                System.out.println("Makes incompatible interfaces work together.");
                System.out.println("In this demo: Adapt a third-party payment processor.");
                break;
            case "6":
                System.out.println("=== Decorator Pattern Help ===");
                System.out.println("Adds behavior to objects dynamically.");
                System.out.println("In this demo: Decorate messages with compression/encryption.");
                break;
            default:
                System.out.println("Unknown pattern: " + pattern + ". Use 1-6.");
        }
    }

    private static void shutdownGracefully(ExecutorService... services) {
        for (ExecutorService s : services) {
            try {
                s.shutdownNow();
            } catch (Exception e) {
                logger.warn("Error shutting down service", e);
            }
        }
    }
}
