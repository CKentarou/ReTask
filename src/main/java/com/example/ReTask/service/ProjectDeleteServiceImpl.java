package com.example.ReTask.service;

import com.example.ReTask.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectDeleteServiceImpl implements ProjectDeleteService{

    private final ProjectRepository repository;

    @Override
    public void deleteProject(int projectId) {
        repository.deleteById(projectId);
    }
}
