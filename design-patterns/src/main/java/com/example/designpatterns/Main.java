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
                        case "exit":
                            logger.info("Exit requested. Shutting down...");
                            running = false;
                            break;
                        default:
                            System.out.println("Unknown command: " + cmd + ". type 'menu' to show options or 'exit' to quit.");
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
        System.out.println("exit - stop the app");
        System.out.println("Enter choice: ");
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
