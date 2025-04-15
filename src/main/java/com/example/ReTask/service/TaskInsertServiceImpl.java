package com.example.ReTask.service;

import com.example.ReTask.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskInsertServiceImpl implements TaskInsertService{

    private final TaskRepository repository;

    @Override
    public void addTaskToSession(int sessionId, String taskName) {
        repository.addTaskToSession(sessionId, taskName);
    }

    @Override
    public void updateTaskStatus(int taskId, String status) {
        repository.updateTaskStatus(taskId, status);
    }
}
