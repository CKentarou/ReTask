package com.example.ReTask.service;

import com.example.ReTask.entity.Project;
import com.example.ReTask.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectListServiceImpl implements ProjectListService{

    private final ProjectRepository repository;

    @Override
    public List<Project> findAll() {
        List<Project> list = repository.findAll();

        return list;
    }
}
