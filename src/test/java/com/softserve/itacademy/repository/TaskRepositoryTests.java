package com.softserve.itacademy.repository;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class TaskRepositoryTests {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    void testGetByTodoId() {
        long todoId = 1L;
        List<Task> tasks = new ArrayList<>();
        Task task1 = new Task();
        task1.setId(1L);
        Task task2 = new Task();
        task2.setId(2L);
        tasks.add(task1);
        tasks.add(task2);

        when(taskRepository.getByTodoId(todoId)).thenReturn(tasks);

        List<Task> resultTasks = taskService.getByTodoId(todoId);

        assertNotNull(resultTasks);
        assertEquals(2, resultTasks.size());

        verify(taskRepository, times(1)).getByTodoId(todoId);
    }
}