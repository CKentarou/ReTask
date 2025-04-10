package com.example.ReTask.controller;

import com.example.ReTask.entity.Project;
import com.example.ReTask.form.ProjectDeleteForm;
import com.example.ReTask.form.ProjectDetailForm;
import com.example.ReTask.form.ProjectListForm;
import com.example.ReTask.form.SessionInitForm;
import com.example.ReTask.service.ProjectDeleteService;
import com.example.ReTask.service.ProjectSearchService;
import com.example.ReTask.service.ProjectRegistService;
import com.example.ReTask.service.SessionGetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProjectController {

    private final ProjectSearchService projectSearchService;
    private final ProjectRegistService registService;
    private final ProjectDeleteService deleteService;
    private final SessionGetService sessionGetService;

    public ProjectController(ProjectSearchService projectSearchService, ProjectRegistService registService, ProjectDeleteService deleteService, SessionGetService sessionGetService) {
        this.projectSearchService = projectSearchService;
        this.registService = registService;
        this.deleteService = deleteService;
        this.sessionGetService = sessionGetService;
    }

    @GetMapping("/projects")
    public String projectList(Model model) {

        List<Project> list = projectSearchService.findAll();

        model.addAttribute("projectList", list);
        model.addAttribute("title", "プロジェクト一覧");
        return "project-list";
    }

    @GetMapping("/project/create")
    public String showProjectForm(@ModelAttribute ProjectListForm form, Model model) {
        model.addAttribute("title", "プロジェクト作成");
        return "project-create";
    }

    @PostMapping("/project/create")
    public String projectCreate(@ModelAttribute ProjectListForm form, Model model) {
        Project project = form.toProject();
        registService.regist(project);
        return "redirect:/projects";
    }

    @PostMapping("/project/detail")
    public String showProjectDetail(@ModelAttribute ProjectDetailForm projectForm, Model model) {
        int projectId = projectForm.getProjectId();

        return "redirect:/project/detail?projectId=" + projectId;
    }

    @GetMapping("/project/detail")
    public String showProjectDetail(@RequestParam("projectId") int projectId, Model model) {

        Project project = projectSearchService.findProjectById(projectId);
        int sessionCount = sessionGetService.getSessionCount(projectId);

        model.addAttribute("project", project);
        model.addAttribute("sessionCount", sessionCount);
        model.addAttribute("title", "プロジェクト詳細");
        return "project-detail";
    }

    @PostMapping("/project/delete")
    public String projectDelete(ProjectDeleteForm form) {
        deleteService.deleteProject(form.getProjectId());

        return "redirect:/projects";
    }
}
