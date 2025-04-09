package com.example.ReTask.controller;

import com.example.ReTask.entity.Project;
import com.example.ReTask.service.ProjectListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectListService service;

    @GetMapping("/projects")
    public String projectList(Model model) {

        List<Project> list = service.findAll();

        model.addAttribute("projectList", list);
        model.addAttribute("title", "プロジェクト一覧");
        return "project-list";
    }

    @GetMapping("/project/create")
    public String projectCreate() {
        return "project-create";
    }
}
