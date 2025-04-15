package com.example.ReTask.controller;

import com.example.ReTask.entity.Task;
import com.example.ReTask.service.TaskGetService;
import com.example.ReTask.service.TaskInsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class TaskController {

    private final TaskInsertService taskInsertServis;
    private final TaskGetService taskGetService;

    public TaskController(TaskInsertService taskInsertServis, TaskGetService taskGetService) {
        this.taskInsertServis = taskInsertServis;
        this.taskGetService = taskGetService;
    }

    @PostMapping("/api/task/{sessionId}")
    @ResponseBody
    public void addTaskToSession(@PathVariable int sessionId, @RequestBody Map<String, String> requestBody) {
        String taskName = requestBody.get("name");
        taskInsertServis.addTaskToSession(sessionId, taskName);
    }

    @GetMapping("/api/task/{sessionId}")
    @ResponseBody
    public List<Task> getTasksBySessionId(@PathVariable int sessionId) {
        return taskGetService.getTasksBySessionId(sessionId);
    }

    @PutMapping("/api/task/{taskId}/status")
    @ResponseBody
    public void updateTaskStatus(@PathVariable int taskId, @RequestBody Map<String, String> requestBody) {
        String status = requestBody.get("status");
        taskInsertServis.updateTaskStatus(taskId, status);
    }
}
