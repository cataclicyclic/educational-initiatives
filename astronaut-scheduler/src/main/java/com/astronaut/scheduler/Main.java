package com.astronaut.scheduler;

import com.astronaut.scheduler.factory.TaskFactory;
import com.astronaut.scheduler.manager.ScheduleManager;
import com.astronaut.scheduler.models.Priority;
import com.astronaut.scheduler.models.Task;
import com.astronaut.scheduler.observer.ConflictNotifier;
import com.astronaut.scheduler.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Astronaut Scheduler starting...");

        ScheduleManager manager = ScheduleManager.getInstance();
        manager.registerObserver(new ConflictNotifier());

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            printMenu();
            String line;
            boolean running = true;
            while (running && (line = br.readLine()) != null) {
                String cmd = line.trim();
                try {
                    switch (cmd) {
                        case "1": // add
                            handleAdd(br, manager);
                            break;
                        case "2": // remove
                            handleRemove(br, manager);
                            break;
                        case "3": // view
                            handleView(manager);
                            break;
                        case "4": // edit
                            handleEdit(br, manager);
                            break;
                        case "5": // mark completed
                            handleMarkCompleted(br, manager);
                            break;
                        case "6": // view by priority
                            handleViewByPriority(br, manager);
                            break;
                        case "menu":
                            printMenu();
                            break;
                        case "exit":
                            running = false;
                            break;
                        default:
                            System.out.println("Unknown command. Type 'menu' to see options.");
                    }
                } catch (InvalidTimeException | TaskConflictException | TaskNotFoundException ex) {
                    logger.warn("Operation failed: {}", ex.getMessage());
                    System.out.println("ERROR: " + ex.getMessage());
                } catch (Exception ex) {
                    logger.error("Unexpected error", ex);
                    System.out.println("An unexpected error occurred. Check logs.");
                }
                if (running) System.out.print("\nEnter next command: ");
            }
        } catch (Exception e) {
            logger.error("Fatal error in main loop", e);
        }
        logger.info("Astronaut Scheduler stopped.");
    }

    private static void handleAdd(BufferedReader br, ScheduleManager manager) throws Exception {
        System.out.print("Description: ");
        String desc = br.readLine().trim();
        System.out.print("Start time (HH:mm): ");
        String start = br.readLine().trim();
        System.out.print("End time (HH:mm): ");
        String end = br.readLine().trim();
        System.out.print("Priority (HIGH, MEDIUM, LOW): ");
        String pr = br.readLine().trim();

        Task t = TaskFactory.createTask(desc, start, end, Priority.valueOf(pr.toUpperCase()));
        manager.addTask(t);
        System.out.println("Task added successfully.");
    }

    private static void handleRemove(BufferedReader br, ScheduleManager manager) throws Exception {
        System.out.print("Task ID to remove: ");
        String id = br.readLine().trim();
        manager.removeTask(id);
        System.out.println("Task removed successfully.");
    }

    private static void handleView(ScheduleManager manager) {
        List<Task> tasks = manager.viewTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
            return;
        }
        System.out.println("Scheduled tasks:");
        for (Task t : tasks) {
            System.out.printf("ID: %s | %s - %s: %s [%s] %s\n",
                    t.getId(), t.getStart().toString(), t.getEnd().toString(), t.getDescription(), t.getPriority(), t.isCompleted() ? "(Completed)" : "");
        }
    }

    private static void handleEdit(BufferedReader br, ScheduleManager manager) throws Exception {
        System.out.print("Task ID to edit: ");
        String id = br.readLine().trim();
        System.out.print("New Description (leave blank to keep): ");
        String desc = br.readLine().trim();
        System.out.print("New Start time (HH:mm) (leave blank to keep): ");
        String start = br.readLine().trim();
        System.out.print("New End time (HH:mm) (leave blank to keep): ");
        String end = br.readLine().trim();
        System.out.print("New Priority (HIGH, MEDIUM, LOW) (leave blank to keep): ");
        String pr = br.readLine().trim();

        manager.editTask(id, desc.isBlank() ? null : desc,
                start.isBlank() ? null : LocalTime.parse(start),
                end.isBlank() ? null : LocalTime.parse(end),
                pr.isBlank() ? null : Priority.valueOf(pr.toUpperCase()));
        System.out.println("Task edited successfully.");
    }

    private static void handleMarkCompleted(BufferedReader br, ScheduleManager manager) throws Exception {
        System.out.print("Task ID to mark completed: ");
        String id = br.readLine().trim();
        manager.markCompleted(id);
        System.out.println("Task marked completed.");
    }

    private static void handleViewByPriority(BufferedReader br, ScheduleManager manager) {
        try {
            System.out.print("Priority (HIGH, MEDIUM, LOW): ");
            String pr = br.readLine().trim();
            List<Task> tasks = manager.viewTasksByPriority(Priority.valueOf(pr.toUpperCase()));
            if (tasks.isEmpty()) {
                System.out.println("No tasks found for the given priority.");
                return;
            }
            for (Task t : tasks) {
                System.out.printf("%s - %s: %s [%s]\n",
                        t.getStart().toString(), t.getEnd().toString(), t.getDescription(), t.getPriority());
            }
        } catch (Exception e) {
            System.out.println("Invalid priority. Use HIGH, MEDIUM or LOW.");
        }
    }

    private static void printMenu() {
        System.out.println("=== Astronaut Daily Schedule Organizer ===");
        System.out.println("1 - Add Task");
        System.out.println("2 - Remove Task (by ID)");
        System.out.println("3 - View All Tasks");
        System.out.println("4 - Edit Task");
        System.out.println("5 - Mark Task Completed");
        System.out.println("6 - View Tasks by Priority");
        System.out.println("menu - show this menu");
        System.out.println("exit - quit");
        System.out.print("Enter command: ");
    }
}
