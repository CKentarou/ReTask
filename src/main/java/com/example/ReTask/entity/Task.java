package com.example.ReTask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Integer taskId;
    private Integer sessionId;
    private String taskName;
    private Status status;
}
