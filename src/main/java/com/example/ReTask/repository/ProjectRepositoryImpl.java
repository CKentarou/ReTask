package com.example.ReTask.repository;

import com.example.ReTask.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Project> findAll() {
        String sql = "SELECT * FROM projects";

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

        List<Project> result = new ArrayList<Project>();
        for (Map<String, Object> one : list) {
            Project project = new Project();
            project.setProjectId(((Number) one.get("id")).intValue());
            project.setProjectName((String) one.get("name"));
            project.setProjectDescription((String) one.get("description"));

            result.add(project);
        }

        return result;
    }

    @Override
    public Project findProjectById(Integer projectId) {
        String sql = "SELECT * FROM projects WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Project project = new Project();
            project.setProjectId(rs.getInt("id"));
            project.setProjectName(rs.getString("name"));
            project.setProjectDescription(rs.getString("description"));
            return project;
        }, projectId);
    }

    @Override
    public void save(Project project) {
        String sql = "INSERT INTO projects (name, description) VALUES (?, ?)";

        jdbcTemplate.update(sql, project.getProjectName(), project.getProjectDescription());
    }

    @Override
    public void deleteById(Integer projectId) {
        String sql = "DELETE FROM projects WHERE id = ?";

        jdbcTemplate.update(sql, projectId);
    }


}
