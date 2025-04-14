package com.example.ReTask.repository;

import com.example.ReTask.entity.Status;
import com.example.ReTask.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void addTaskToSession(int sessionId, String taskName) {
        String sql = "INSERT INTO tasks (work_session_id, task_name) VALUES (?, ?)";

        jdbcTemplate.update(sql, sessionId, taskName);
    }

    @Override
    public List<Task> getTasksBySessionId(int sessionId) {
        String sql = "SELECT * FROM tasks WHERE work_session_id = ? AND status != 'DELETED'";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, sessionId);
        List<Task> result = new java.util.ArrayList<Task>();

        for (Map<String, Object> one : list) {
            Task task = new Task();
            task.setTaskId(((Number) one.get("id")).intValue());
            task.setSessionId(((Number) one.get("work_session_id")).intValue());
            task.setTaskName((String) one.get("task_name"));
            task.setStatus(Status.valueOf((String) one.get("status")));
            result.add(task);
        }

        return result;
    }
}
