package com.example.ReTask.service;

import com.example.ReTask.entity.Project;

import java.util.List;

public interface ProjectSearchService {
    //プロジェクトの全件取得
    List<Project> findAll();

    Project findProjectById(Integer projectId);
}
