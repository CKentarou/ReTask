package com.example.ReTask.service;

import com.example.ReTask.entity.Task;

import java.util.List;

public interface TaskGetService {
    List<Task> getTasksBySessionId(int sessionId);
    int getTaskCountByStatus(int sessionId, String status);
}
