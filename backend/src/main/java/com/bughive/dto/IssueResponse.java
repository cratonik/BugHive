package com.bughive.dto;

import com.bughive.entity.IssuePriority;
import com.bughive.entity.IssueStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IssueResponse {
    private Long id;
    private String title;
    private String description;
    private IssueStatus status;
    private IssuePriority priority;

    private Long projectId;
    private String projectName;

    private Long assigneeId;   // nullable

    private LocalDateTime createdAt;
}
