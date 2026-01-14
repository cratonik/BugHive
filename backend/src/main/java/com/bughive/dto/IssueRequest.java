package com.bughive.dto;

import com.bughive.entity.IssuePriority;
import com.bughive.entity.IssueStatus;
import lombok.Data;

@Data
public class IssueRequest {
    private String title;
    private String description;
    private IssueStatus status;
    private IssuePriority priority;
    private Long projectId;
    private Long assigneeId; // nullable

}
