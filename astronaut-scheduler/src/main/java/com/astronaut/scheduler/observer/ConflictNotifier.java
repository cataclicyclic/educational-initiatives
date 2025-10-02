package com.astronaut.scheduler.observer;

import com.astronaut.scheduler.models.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConflictNotifier implements TaskObserver {
    private static final Logger logger = LoggerFactory.getLogger(ConflictNotifier.class);

    @Override
    public void notify(Task newTask, Task conflictingTask) {
        String msg = String.format("Conflict: New task '%s' (%s-%s) conflicts with existing task '%s' (%s-%s)",
                newTask.getDescription(), newTask.getStart(), newTask.getEnd(),
                conflictingTask.getDescription(), conflictingTask.getStart(), conflictingTask.getEnd());
        // Log and print user-friendly message
        logger.warn(msg);
        System.out.println("[ConflictNotifier] " + msg);
    }
}
