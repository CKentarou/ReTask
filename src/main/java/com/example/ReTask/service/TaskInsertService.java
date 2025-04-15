package com.example.ReTask.service;

public interface TaskInsertService {
    void addTaskToSession(int sessionId, String taskName);
    void updateTaskStatus(int taskId, String status);
}
