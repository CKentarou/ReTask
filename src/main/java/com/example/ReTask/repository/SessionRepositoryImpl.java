package com.example.ReTask.repository;

import com.example.ReTask.entity.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SessionRepositoryImpl implements SessionRepository{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int getSessionCount(int projectId) {
        String sql = "SELECT COUNT(*) FROM work_sessions WHERE project_id = ?";
        int sessionCount = jdbcTemplate.queryForObject(sql, Integer.class, projectId);

        return sessionCount;
    }

    @Override
    public void insertSession(Session session) {
        String sql = "INSERT INTO work_sessions (project_id, session_date, session_number) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, session.getProjectId(), session.getSessionDate(), session.getSessionNumber());
    }
}
