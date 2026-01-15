package com.bughive.mapper;

import com.bughive.dto.IssueRequest;
import com.bughive.dto.IssueResponse;
import com.bughive.entity.Issue;
import com.bughive.entity.Project;
import com.bughive.entity.User;
import org.springframework.stereotype.Component;

@Component
public class IssueMapper {

    public IssueResponse toDto(Issue issue) {
        IssueResponse dto = new IssueResponse();

        dto.setId(issue.getId());
        dto.setTitle(issue.getTitle());
        dto.setDescription(issue.getDescription());
        dto.setStatus(issue.getStatus());
        dto.setPriority(issue.getPriority());

        // Project (guaranteed by fetch join)
        dto.setProjectId(issue.getProject().getId());
        dto.setProjectName(issue.getProject().getName());

        // Assignee (nullable by design)
        dto.setAssigneeId(
                issue.getAssignee() != null ? issue.getAssignee().getId() : null);

        return dto;
    }

    public Issue toEntity(IssueRequest request, Project project,
                          User assignee) {
        Issue issue = new Issue();
        issue.setTitle(request.getTitle());
        issue.setDescription(request.getDescription());
        issue.setStatus(request.getStatus());
        issue.setPriority(request.getPriority());
        issue.setProject(project);
        issue.setAssignee(assignee);
        return issue;
    }
}
