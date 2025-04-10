package com.example.ReTask.controller;

import com.example.ReTask.entity.Session;
import com.example.ReTask.form.SessionInitForm;
import com.example.ReTask.service.SessionGetService;
import com.example.ReTask.service.SessionInsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller

public class sessionController {
    private final SessionInsertService initService;
    private final SessionGetService sessionGetService;

    public sessionController(SessionInsertService initService, SessionGetService sessionGetService) {
        this.initService = initService;
        this.sessionGetService = sessionGetService;
    }

    @PostMapping("/new-session")
    public String showSession(@ModelAttribute SessionInitForm form, RedirectAttributes redirectAttributes, Model model) {
        Session session = form.toSession(form.getSessionCount());
        int sessionId = initService.initSession(session);
//        redirectAttributes.addFlashAttribute("sessionId", sessionId);
        return "redirect:/new-session?sessionId=" + sessionId;
    }

    @GetMapping("/new-session")
    public String showSessionPage(@RequestParam("sessionId") int sessionId, Model model) {
        //作成したsessionをDBから取得してモデルに追加する
        Session session = sessionGetService.getSessionBySessionId(sessionId);
        System.out.println(session);
        model.addAttribute("session", session);
        return "new-session";
    }
}
