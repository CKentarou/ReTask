package com.example.ReTask.repository;

import com.example.ReTask.entity.Task;

import java.util.List;

public interface TaskRepository {
    void addTaskToSession(int sessionId, String taskName);
    List<Task> getTasksBySessionId(int sessionId);
    List<Task> getTasksBySessionIdAndStatus(int sessionId, String status);
    void updateTaskStatus(int taskId, String status);
    int getTaskCountByStatus(int sessionId, String status);
}
