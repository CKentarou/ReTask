package com.example.ReTask.service;

import com.example.ReTask.entity.Session;
import com.example.ReTask.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionInsertServiceImpl implements SessionInsertService {
    private final SessionRepository repository;

    @Override
    public int initSession(Session session) {
        return repository.initSession(session);
    }
}
