package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exceptions.Role.RoleNotFoundException;
import com.softserve.itacademy.exceptions.State.StateNotFoundException;
import com.softserve.itacademy.exceptions.State.StateNullException;
import com.softserve.itacademy.exceptions.Task.TaskAlreadyExsistsException;
import com.softserve.itacademy.exceptions.Task.TaskNotFoundException;
import com.softserve.itacademy.exceptions.Task.TaskNullException;
import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.State;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;
import com.softserve.itacademy.service.TaskService;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task task) {
        if (task == null) {
            throw new TaskNullException();
        }
        if (taskRepository.findById(task.getId()).isPresent()) {
            throw new TaskAlreadyExsistsException(task.getId());
        }
        return taskRepository.save(task);

    }

    @Override
    public Task readById(long id) {
        if (!taskRepository.findById(id).isPresent()) {
            throw new TaskNotFoundException(id);
        }
        return taskRepository.findById(id).get();
    }


    @Override
    public Task update(Task task) {
        if (task == null) {
            throw new TaskNullException();
        }
        Optional<Task> taskOptional = taskRepository.findById(task.getId());
        if (!taskOptional.isPresent()) {
            throw new TaskNotFoundException(task.getId());
        }
        Task result = taskOptional.get();
        result.setName(task.getName());
        result.setState(task.getState());
        return taskRepository.save(result);
    }

    @Override
    public void delete(long id) {
        if (!taskRepository.findById(id).isPresent()) {
            throw new TaskNotFoundException(id);
        }
        Task result = taskRepository.findById(id).get();
        taskRepository.delete(result);

    }

    @Override
    public List<Task> getAll() {

        return taskRepository.findAll();
    }

    @Override
    @Transactional
    public List<Task> getByTodoId(long todoId) {
        List<Task> tasks = taskRepository.getByTodoId(todoId);

        if (tasks.isEmpty()) {
            throw new TaskNullException();
        }

        return tasks;
    }
}
