package com.example.ReTask.controller;

import com.example.ReTask.entity.Session;
import com.example.ReTask.entity.Task;
import com.example.ReTask.form.SessionInitForm;
import com.example.ReTask.service.SessionGetService;
import com.example.ReTask.service.SessionInsertService;
import com.example.ReTask.service.TaskGetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Controller

public class SessionController {
    private final SessionInsertService sessionInsertService;
    private final SessionGetService sessionGetService;
    private final TaskGetService taskGetService;

    public SessionController(SessionInsertService sessionInsertService, SessionGetService sessionGetService, TaskGetService taskGetService) {
        this.sessionInsertService = sessionInsertService;
        this.sessionGetService = sessionGetService;
        this.taskGetService = taskGetService;
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
        List<Task> completedTasks = taskGetService.getTasksBySessionIdAndStatus(sessionId, "COMPLETED");
        List<Task> incompleteTasks = taskGetService.getTasksBySessionIdAndStatus(sessionId, "PENDING");

        // 仮のデータ（DBから取得する場合はリポジトリを使用）
        int completedCount = taskGetService.getTaskCountByStatus(sessionId, "COMPLETED"); // 完了タスク数
        int incompleteCount = taskGetService.getTaskCountByStatus(sessionId, "PENDING"); // 未完了タスク数

        // モデルにデータを追加
        model.addAttribute("completedTasks", completedTasks);
        model.addAttribute("incompleteTasks", incompleteTasks);
        model.addAttribute("completedCount", completedCount);
        model.addAttribute("incompleteCount", incompleteCount);
        model.addAttribute("title", "セッション結果");

        sessionInsertService.updateSessionEndTime(sessionId, endTime);
        return "session-result";
    }
}
