package com.example.ReTask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    private Integer projectId;
    private Date sessionDate;
    private Integer sessionNumber;
    private Date started_at;
    private Date ended_at;
}
