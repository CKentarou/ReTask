package com.example.ReTask.controller;

import com.example.ReTask.service.TaskInsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskInsertService service;

    @PostMapping("/api/task/{sessionId}")
    @ResponseBody
    public void addTaskToSession(@PathVariable int sessionId, @RequestBody Map<String, String> requestBody) {
        String taskName = requestBody.get("name");

        service.addTaskToSession(sessionId, taskName);
    }
}
