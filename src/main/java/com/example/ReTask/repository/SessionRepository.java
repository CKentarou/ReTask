package com.example.ReTask.repository;

import com.example.ReTask.entity.Session;

public interface SessionRepository {
    int getSessionCount(Integer projectId);
    Session getSessionBySessionId(Integer sessionId);
    int initSession(Session session);
}
