package com.softserve.itacademy.exceptions.Task;

public class TaskAlreadyExsistsException extends RuntimeException{

    private long taskId;

    public TaskAlreadyExsistsException(long taskId){
        super("The task with the ID " + taskId + " already exists.");
        this.taskId= taskId;
    }

    public long getTaskId(){
        return taskId;
    }
}


