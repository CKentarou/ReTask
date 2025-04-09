package com.example.ReTask.service;

import com.example.ReTask.entity.Session;
import com.example.ReTask.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionInitServiceImpl implements SessionInitService{
    private final SessionRepository repository;

    @Override
    public void initSession(Session session) {
        repository.insertSession(session);
    }
}
