package com.example.ReTask.controller;

import com.example.ReTask.entity.Session;
import com.example.ReTask.form.SessionInitForm;
import com.example.ReTask.service.SessionGetService;
import com.example.ReTask.service.SessionInsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller

public class sessionController {
    private final SessionInsertService initService;
    private final SessionGetService sessionGetService;

    public sessionController(SessionInsertService initService, SessionGetService sessionGetService) {
        this.initService = initService;
        this.sessionGetService = sessionGetService;
    }

    @PostMapping("/project/{projectId}/sessions")
    public String showSession(@PathVariable int projectId, @ModelAttribute SessionInitForm form, Model model) {
        Session session = form.toSession(projectId);
        int sessionId = initService.initSession(session);
        return "redirect:/project/" + projectId + "/session/" + sessionId;
    }

    @GetMapping("/project/{projectId}/session/{sessionId}")
    public String showSessionPage(@PathVariable int sessionId, Model model) {
        //作成したsessionをDBから取得してモデルに追加する
        Session session = sessionGetService.getSessionBySessionId(sessionId);
        model.addAttribute("session", session);
        return "session";
    }
}
