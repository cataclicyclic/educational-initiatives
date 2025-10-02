package com.astronaut.scheduler.observer;

import com.astronaut.scheduler.models.Task;

public interface TaskObserver {
    void notify(Task newTask, Task conflictingTask);
}
