package com.astronaut.scheduler.models;

import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

public class Task {
    private final String id;
    private String description;
    private LocalTime start;
    private LocalTime end;
    private Priority priority;
    private boolean completed;

    public Task(String description, LocalTime start, LocalTime end, Priority priority) {
        this.id = UUID.randomUUID().toString();
        this.description = Objects.requireNonNull(description, "description required");
        this.start = Objects.requireNonNull(start, "start required");
        this.end = Objects.requireNonNull(end, "end required");
        this.priority = Objects.requireNonNull(priority, "priority required");
        this.completed = false;
    }

    public String getId() { return id; }
    public String getDescription() { return description; }
    public LocalTime getStart() { return start; }
    public LocalTime getEnd() { return end; }
    public Priority getPriority() { return priority; }
    public boolean isCompleted() { return completed; }

    public void setDescription(String description) { this.description = description; }
    public void setStart(LocalTime start) { this.start = start; }
    public void setEnd(LocalTime end) { this.end = end; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public boolean overlaps(Task other) {
        // Overlap if start < other.end && other.start < end
        return this.start.isBefore(other.end) && other.start.isBefore(this.end);
    }
}
