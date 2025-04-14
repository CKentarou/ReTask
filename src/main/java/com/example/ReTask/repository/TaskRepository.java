package com.example.ReTask.repository;

public interface TaskRepository {
    void addTaskToSession(int sessionId, String taskName);
}
