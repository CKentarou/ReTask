package com.example.ReTask.service;

import com.example.ReTask.entity.Project;
import com.example.ReTask.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectRegistServiceImpl implements ProjectRegistService{
    private final ProjectRepository repository;
    @Override
    public void regist(Project project) {
        repository.save(project);
    }
}
