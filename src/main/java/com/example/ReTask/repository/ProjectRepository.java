package com.example.ReTask.repository;

import com.example.ReTask.entity.Project;

import java.util.List;

public interface ProjectRepository {
    List<Project> findAll();
    Project findProjectById(Integer projectId);
    void save(Project project);
    void deleteById(Integer projectId);
}
