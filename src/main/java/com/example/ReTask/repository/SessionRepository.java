package com.example.ReTask.repository;

import com.example.ReTask.entity.Session;

import java.sql.Timestamp;

public interface SessionRepository {
    int getSessionCount(Integer projectId);
    Session getSessionBySessionId(Integer sessionId);
    int initSession(Session session);
    void updateSessionStartTime(Integer sessionId, Timestamp startTime);
    void updateSessionEndTime(Integer sessionId, Timestamp endTime);
}
