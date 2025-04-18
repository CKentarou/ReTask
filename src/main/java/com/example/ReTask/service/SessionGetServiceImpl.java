package com.example.ReTask.service;

import com.example.ReTask.entity.Session;
import com.example.ReTask.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionGetServiceImpl implements SessionGetService {
    private final SessionRepository repository;

    @Override
    public int getSessionCount(int projectId) {
        return repository.getSessionCount(projectId);
    }

    @Override
    public Session getSessionBySessionId(int sessionId) {
        return repository.getSessionBySessionId(sessionId);
    }
}
