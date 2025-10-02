package com.astronaut.scheduler.manager;

import com.astronaut.scheduler.models.Task;
import com.astronaut.scheduler.models.Priority;
import com.astronaut.scheduler.observer.TaskObserver;
import com.astronaut.scheduler.exceptions.TaskConflictException;
import com.astronaut.scheduler.exceptions.TaskNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ScheduleManager {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleManager.class);
    private static final ScheduleManager INSTANCE = new ScheduleManager();

    // thread-safe collection for concurrent access
    private final List<Task> tasks = new CopyOnWriteArrayList<>();
    private final List<TaskObserver> observers = new CopyOnWriteArrayList<>();

    private ScheduleManager() { }

    public static ScheduleManager getInstance() { return INSTANCE; }

    public void registerObserver(TaskObserver obs) {
        observers.add(obs);
    }

    public void addTask(Task t) throws TaskConflictException {
        // check overlap
        Optional<Task> conflict = tasks.stream().filter(existing -> existing.overlaps(t)).findFirst();
        if (conflict.isPresent()) {
            logger.warn("Task conflict detected: {} conflicts with {}", t.getDescription(), conflict.get().getDescription());
            notifyObservers(t, conflict.get());
            throw new TaskConflictException("Task conflicts with existing task '" + conflict.get().getDescription() + "'.");
        }
        tasks.add(t);
        logger.info("Task added: {} ({} - {})", t.getDescription(), t.getStart(), t.getEnd());
    }

    public void removeTask(String id) throws TaskNotFoundException {
        boolean removed = tasks.removeIf(task -> task.getId().equals(id));
        if (!removed) throw new TaskNotFoundException("Task not found: " + id);
        logger.info("Removed task id={}", id);
    }

    public List<Task> viewTasks() {
        return tasks.stream().sorted(Comparator.comparing(Task::getStart)).collect(Collectors.toList());
    }

    public void editTask(String id, String description, LocalTime start, LocalTime end, Priority priority) throws TaskNotFoundException, TaskConflictException {
        Task t = findById(id);
        Task temp = new Task(description == null ? t.getDescription() : description,
                start == null ? t.getStart() : start,
                end == null ? t.getEnd() : end,
                priority == null ? t.getPriority() : priority);
        // ensure edits don't conflict with others (excluding self)
        Optional<Task> conflict = tasks.stream().filter(existing -> !existing.getId().equals(id) && existing.overlaps(temp)).findFirst();
        if (conflict.isPresent()) {
            notifyObservers(temp, conflict.get());
            throw new TaskConflictException("Edited task conflicts with existing task '" + conflict.get().getDescription() + "'.");
        }
        // apply changes
        if (description != null) t.setDescription(description);
        if (start != null) t.setStart(start);
        if (end != null) t.setEnd(end);
        if (priority != null) t.setPriority(priority);
        logger.info("Edited task id={}", id);
    }

    public void markCompleted(String id) throws TaskNotFoundException {
        Task t = findById(id);
        t.setCompleted(true);
        logger.info("Task marked completed id={}", id);
    }

    public List<Task> viewTasksByPriority(Priority p) {
        return tasks.stream().filter(t -> t.getPriority() == p).sorted(Comparator.comparing(Task::getStart)).collect(Collectors.toList());
    }

    private Task findById(String id) throws TaskNotFoundException {
        return tasks.stream().filter(t -> t.getId().equals(id)).findFirst().orElseThrow(() -> new TaskNotFoundException("Task not found: " + id));
    }

    private void notifyObservers(Task newTask, Task conflictingTask) {
        for (TaskObserver o : observers) {
            try {
                o.notify(newTask, conflictingTask);
            } catch (Exception e) {
                logger.warn("Observer notification failed: {}", e.getMessage());
            }
        }
    }
}
