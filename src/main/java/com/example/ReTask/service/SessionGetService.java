package com.example.ReTask.service;

import com.example.ReTask.entity.Session;

public interface SessionGetService {
    int getSessionCount(int projectId);
    Session getSessionBySessionId(int sessionId);
}
