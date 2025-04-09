package com.example.ReTask.form;

import com.example.ReTask.entity.Session;
import lombok.Data;

import java.sql.Date;

@Data
public class SessionInitForm {
    private int projectId;
    private int sessionCount;
    private Date sessionDate;

    public Session toSession(int sessionCount) {
        Session session = new Session();
        session.setProjectId(this.projectId);
        session.setSessionNumber(sessionCount + 1);
        session.setSessionDate(new Date(System.currentTimeMillis()));
        return session;
    }
}
