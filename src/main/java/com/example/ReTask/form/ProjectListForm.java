package com.example.ReTask.form;

import com.example.ReTask.entity.Project;
import lombok.Data;

@Data
public class ProjectListForm {
    private String projectName;
    private String projectDescription;

    public Project toProject() {
        Project project = new Project();
        project.setProjectName(this.projectName);
        project.setProjectDescription(this.projectDescription);
        return project;
    }
}
