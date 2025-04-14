package com.example.ReTask.controller;

import com.example.ReTask.entity.Session;
import com.example.ReTask.form.SessionInitForm;
import com.example.ReTask.service.SessionGetService;
import com.example.ReTask.service.SessionInsertService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller

public class SessionController {
    private final SessionInsertService sessionInsertService;
    private final SessionGetService sessionGetService;

    public SessionController(SessionInsertService sessionInsertService, SessionGetService sessionGetService) {
        this.sessionInsertService = sessionInsertService;
        this.sessionGetService = sessionGetService;
    }

    @PostMapping("/project/{projectId}/sessions")
    public String showSession(@PathVariable int projectId, @ModelAttribute SessionInitForm form, Model model) {
        Session session = form.toSession(projectId);
        int sessionId = sessionInsertService.initSession(session);
        return "redirect:/project/" + projectId + "/session/" + sessionId;
    }

    @GetMapping("/project/{projectId}/session/{sessionId}")
    public String showSessionPage(@PathVariable int projectId, @PathVariable int sessionId, Model model) {
        //作成したsessionをDBから取得してモデルに追加する
        Session session = sessionGetService.getSessionBySessionId(sessionId);
        model.addAttribute("sessionId", sessionId);
        model.addAttribute("projectId", projectId);
        model.addAttribute("title", "セッション");
        return "session";
    }

    @PostMapping("/api/start/{sessionId}")
    @ResponseBody
    public void startSession(@PathVariable int sessionId) {
        LocalDateTime nowTime = LocalDateTime.now();
        Timestamp startTime = Timestamp.valueOf(nowTime);

        sessionInsertService.updateSessionStartTime(sessionId, startTime);
    }

    @GetMapping("/project/{projectId}/session/{sessionId}/result")
    public String showSessionResult(@PathVariable int sessionId, Model model) {
        LocalDateTime nowTime = LocalDateTime.now();
        Timestamp endTime = Timestamp.valueOf(nowTime);

        sessionInsertService.updateSessionEndTime(sessionId, endTime);
        return "session-result";
    }
}
