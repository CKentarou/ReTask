package com.example.ReTask.form;

import com.example.ReTask.entity.Session;
import lombok.Data;

import java.sql.Date;

@Data
public class SessionInitForm {
    private int projectId;
    private int sessionCount;
    private Date sessionDate;

    public Session toSession(int projectId) {
        Session session = new Session();
        session.setProjectId(projectId);
        session.setSessionDate(new Date(System.currentTimeMillis()));
        return session;
    }
}
