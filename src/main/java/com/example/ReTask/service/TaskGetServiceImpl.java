package com.example.ReTask.service;

import com.example.ReTask.entity.Task;
import com.example.ReTask.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskGetServiceImpl implements TaskGetService{

    private final TaskRepository repository;

    @Override
    public List<Task> getTasksBySessionId(int sessionId) {
        return repository.getTasksBySessionId(sessionId);
    }

    @Override
    public int getTaskCountByStatus(int sessionId, String status) {
        return repository.getTaskCountByStatus(sessionId, status);
    }
}
