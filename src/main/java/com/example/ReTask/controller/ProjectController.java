package com.example.ReTask.controller;

import com.example.ReTask.entity.Project;
import com.example.ReTask.form.ProjectDeleteForm;
import com.example.ReTask.form.ProjectDetailForm;
import com.example.ReTask.form.ProjectForm;
import com.example.ReTask.service.ProjectDeleteService;
import com.example.ReTask.service.ProjectSearchService;
import com.example.ReTask.service.ProjectRegistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProjectController {

    private final ProjectSearchService searchService;
    private final ProjectRegistService registService;
    private final ProjectDeleteService deleteService;

    public ProjectController(ProjectSearchService searchService, ProjectRegistService registService, ProjectDeleteService deleteService) {
        this.searchService = searchService;
        this.registService = registService;
        this.deleteService = deleteService;
    }

    @GetMapping("/projects")
    public String projectList(Model model) {

        List<Project> list = searchService.findAll();

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
        registService.regist(project);
        return "redirect:/projects";
    }

    @PostMapping("/project/detail")
    public String showProjectDetail(@ModelAttribute ProjectDetailForm form, Model model) {
        Project project = searchService.findProjectById(form.getProjectId());

        model.addAttribute("project", project);
        model.addAttribute("title", "プロジェクト詳細");
        return "project-detail";
    }

    @PostMapping("/project/delete")
    public String projectDelete(ProjectDeleteForm form) {
        deleteService.deleteProject(form.getProjectId());

        return "redirect:/projects";
    }
}
