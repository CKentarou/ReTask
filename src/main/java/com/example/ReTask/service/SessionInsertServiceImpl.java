package com.example.ReTask.service;

import com.example.ReTask.entity.Session;
import com.example.ReTask.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class SessionInsertServiceImpl implements SessionInsertService {
    private final SessionRepository repository;

    @Override
    public int initSession(Session session) {
        return repository.initSession(session);
    }

    @Override
    public void updateSessionStartTime(Integer sessionId, Timestamp startTime) {
        repository.updateSessionStartTime(sessionId, startTime);
    }

    @Override
    public void updateSessionEndTime(Integer sessionId, Timestamp endTime) {
        repository.updateSessionEndTime(sessionId, endTime);
    }
}
