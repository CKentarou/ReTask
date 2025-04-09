package com.example.ReTask.service;

import com.example.ReTask.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionCountServiceImpl implements SessionCountService{
    private final SessionRepository repository;

    @Override
    public int getSessionCount(int projectId) {
        return repository.getSessionCount(projectId);
    }
}
