package com.example.ReTask.controller;

import com.example.ReTask.entity.Session;
import com.example.ReTask.form.SessionInitForm;
import com.example.ReTask.service.SessionInitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class sessionController {
    private final SessionInitService initService;

    @PostMapping("/new-session")
    public String showSession(@ModelAttribute SessionInitForm form, Model model) {
        Session session = form.toSession(form.getSessionCount());
        initService.initSession(session);
        return "new-session";
    }
}
