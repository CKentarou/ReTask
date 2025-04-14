package com.example.ReTask.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void addTaskToSession(int sessionId, String taskName) {
        String sql = "INSERT INTO tasks (work_session_id, task_name) VALUES (?, ?)";

        jdbcTemplate.update(sql, sessionId, taskName);
    }
}
