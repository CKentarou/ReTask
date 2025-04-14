package com.example.ReTask.service;

import com.example.ReTask.entity.Session;

import java.sql.Timestamp;

public interface SessionInsertService {
    int initSession(Session session);
    void updateSessionStartTime(Integer sessionId, Timestamp startTime);
    void updateSessionEndTime(Integer sessionId, Timestamp endTime);
}
