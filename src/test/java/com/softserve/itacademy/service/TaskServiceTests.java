package com.softserve.itacademy.service;

import com.softserve.itacademy.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
class TaskServiceTests {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreate() {
        Task task = new Task();
        task.setId(1L);
        task.setName("Task 1");
        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task createdTask = taskService.create(task);

        assertNotNull(createdTask);
        assertEquals(1L, createdTask.getId());
        assertEquals("Task 1", createdTask.getName());

        verify(taskRepository, times(1)).findById(anyLong());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void testCreate_NullTask() {
        assertThrows(RuntimeException.class, () -> taskService.create(null));
    }

    @Test
    void testCreate_TaskAlreadyExists() {
        Task task = new Task();
        task.setId(1L);
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));

        assertThrows(RuntimeException.class, () -> taskService.create(task));
    }

    @Test
    void testReadById() {
        Task task = new Task();
        task.setId(1L);
        task.setName("Task 1");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task foundTask = taskService.readById(1L);

        assertNotNull(foundTask);
        assertEquals(1L, foundTask.getId());
        assertEquals("Task 1", foundTask.getName());

    }

    @Test
    void testReadById_TaskNotFound() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> taskService.readById(1L));
    }

    @Test
    void testUpdate() {
        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setName("Existing Task");

        Task updatedTask = new Task();
        updatedTask.setId(1L);
        updatedTask.setName("Updated Task");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

        Task result = taskService.update(updatedTask);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Updated Task", result.getName());

        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void testUpdate_NullTask() {
        assertThrows(RuntimeException.class, () -> taskService.update(null));
    }

    @Test
    void testUpdate_TaskNotFound() {
        Task task = new Task();
        task.setId(1L);
        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> taskService.update(task));
    }



    @Test
    void testDelete_TaskNotFound() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> taskService.delete(1L));
    }

    @Test
    void testGetAll() {
        List<Task> tasks = new ArrayList<>();
        Task task1 = new Task();
        task1.setId(1L);
        Task task2 = new Task();
        task2.setId(2L);
        tasks.add(task1);
        tasks.add(task2);

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> allTasks = taskService.getAll();

        assertNotNull(allTasks);
        assertEquals(2, allTasks.size());

        verify(taskRepository, times(1)).findAll();
    }



    @Test
    void testGetByTodoId_NoTasksFound() {
        long todoId = 1L;
        List<Task> tasks = new ArrayList<>();
        when(taskRepository.getByTodoId(todoId)).thenReturn(tasks);

        assertThrows(RuntimeException.class, () -> taskService.getByTodoId(todoId));
    }
}
