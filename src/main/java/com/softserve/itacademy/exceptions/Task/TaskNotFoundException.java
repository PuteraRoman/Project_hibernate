package com.softserve.itacademy.exceptions.Task;

import com.softserve.itacademy.model.Task;

public class TaskNotFoundException extends RuntimeException {
    private long taskId;
    public TaskNotFoundException(long taskId){
        super("Task with ID " + taskId + " is not found");
        this.taskId = taskId;
    }
    public long getTaskId(){
        return this.taskId;
    }
}

