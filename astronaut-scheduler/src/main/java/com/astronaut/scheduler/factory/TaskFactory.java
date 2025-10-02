package com.astronaut.scheduler.factory;

import com.astronaut.scheduler.exceptions.InvalidTimeException;
import com.astronaut.scheduler.models.Priority;
import com.astronaut.scheduler.models.Task;
import com.astronaut.scheduler.utils.TimeUtils;

import java.time.LocalTime;

public class TaskFactory {
    public static Task createTask(String description, String startStr, String endStr, Priority priority) throws InvalidTimeException {
        LocalTime start = TimeUtils.parseTime(startStr);
        LocalTime end = TimeUtils.parseTime(endStr);
        if (!start.isBefore(end)) {
            throw new InvalidTimeException("Start time must be before end time.");
        }
        return new Task(description, start, end, priority);
    }
}
