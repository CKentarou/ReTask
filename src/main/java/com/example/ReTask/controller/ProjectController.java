package com.example.ReTask.controller;

import com.example.ReTask.entity.Project;
import com.example.ReTask.form.ProjectForm;
import com.example.ReTask.service.ProjectListService;
import com.example.ReTask.service.ProjectRegistService;
import com.example.ReTask.service.ProjectRegistServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProjectController {

    private final ProjectListService selectservice;
    private final ProjectRegistService registservice;

    public ProjectController(ProjectListService selectservice, ProjectRegistService registservice) {
        this.selectservice = selectservice;
        this.registservice = registservice;
    }

    @GetMapping("/projects")
    public String projectList(Model model) {

        List<Project> list = selectservice.findAll();

        model.addAttribute("projectList", list);
        model.addAttribute("title", "プロジェクト一覧");
        return "project-list";
    }

    @GetMapping("/project/create")
    public String showProjectForm(@ModelAttribute ProjectForm form, Model model) {
        model.addAttribute("title", "プロジェクト作成");
        return "project-create";
    }

    @PostMapping("/project/create")
    public String projectCreate(@ModelAttribute ProjectForm form, Model model) {
        Project project = form.toProject();
        registservice.regist(project);
        return "redirect:/projects";
    }

}
