package com.example.ReTask.repository;

import com.example.ReTask.entity.Project;
import com.example.ReTask.entity.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
@RequiredArgsConstructor
public class SessionRepositoryImpl implements SessionRepository{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int getSessionCount(Integer projectId) {
        String sql = "SELECT COUNT(*) FROM work_sessions WHERE project_id = ?";
        int sessionCount = jdbcTemplate.queryForObject(sql, Integer.class, projectId);

        return sessionCount;
    }

    @Override
    public Session getSessionBySessionId(Integer sessionId) {
        String sql = "SELECT * FROM work_sessions WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Session session = new Session();
            session.setSessionId(rs.getInt("id"));
            session.setProjectId(rs.getInt("project_id"));
            session.setSessionDate(rs.getDate("session_date"));
            session.setSessionNumber(rs.getInt("session_number"));
            session.setStarted_at(rs.getDate("started_at"));
            session.setEnded_at(rs.getDate("ended_at"));
            return session;
        }, sessionId);
    }

    @Override
    public int initSession(Session session) {
        // 新しい作業セッションをDBに挿入し、自動生成されたIDを取得して返す
        String sql = "INSERT INTO work_sessions (project_id, session_date, session_number) VALUES (?, ?, ?)";

        // 自動生成された主キー（ID）を受け取るためのKeyHolderを準備
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // SQLを実行してレコードを挿入（ラムダ式でPreparedStatementを定義）
        jdbcTemplate.update(connection -> {
            // 自動生成されたキーを取得できるようにPreparedStatementを作成
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // プレースホルダーに値をバインド（?の順番に注意）
            ps.setInt(1, session.getProjectId());          // プロジェクトID
            ps.setDate(2, session.getSessionDate());       // セッション日付（java.sql.Date）
            ps.setInt(3, session.getSessionNumber());      // セッション番号（その日の何回目かなど）

            return ps; // 準備したStatementを返す
        }, keyHolder);

        // 自動生成されたIDを取得して返す（例: AUTO_INCREMENTされた主キー）
        return keyHolder.getKey().intValue();
    }
}
