package com.softserve.itacademy.exceptions.Task;

public class TaskNullException extends RuntimeException{
    public TaskNullException(){
        super("The task object is null.");
    }
}
