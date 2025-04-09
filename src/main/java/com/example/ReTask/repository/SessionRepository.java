package com.example.ReTask.repository;

import com.example.ReTask.entity.Session;

public interface SessionRepository {
    int getSessionCount(int projectId);
    void insertSession(Session session);
}
