package com.example.ReTask.repository;

import com.example.ReTask.entity.Project;

import java.util.List;

public interface ProjectRepository {
    List<Project> findAll();
    void save(Project project);
}
