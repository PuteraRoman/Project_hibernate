package com.softserve.itacademy.model;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static junit.framework.TestCase.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTests {

    @Test
    public void testTest() {
        assertTrue(true);
    }

    @Test
    public void testSetAndGetId() {
        Long id = 1L;
        Task task = new Task();
        task.setId(id);

        assertEquals(Optional.of(id), Optional.of(task.getId()));
    }

    @Test
    public void testSetAndGetName() {
        String name = "Task 1";
        Task task = new Task();
        task.setName(name);

        assertEquals(name, task.getName());
    }


    @Test
    public void testSetAndGetTodo() {
        ToDo todo = new ToDo();
        Task task = new Task();
        task.setTodo(todo);

        assertEquals(todo, task.getToDo());
    }

    @Test
    public void testSetAndGetPriority() {
        Priority priority = Priority.HIGH;
        Task task = new Task();
        task.setPriority(priority);

        assertEquals(priority, task.getPriority());
    }

}