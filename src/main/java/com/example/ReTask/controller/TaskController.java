package com.example.ReTask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class TaskController {
    @PostMapping("/api/task/{sessionId}")
    @ResponseBody
    public void addTaskToSession(@PathVariable int sessionId, @RequestBody Map<String, String> requestBody) {
        String taskName = requestBody.get("name");

        System.out.println("session ID :" + sessionId + " task name :" + taskName);
    }
}
