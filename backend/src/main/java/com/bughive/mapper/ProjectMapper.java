package com.bughive.mapper;

import com.bughive.dto.ProjectRequest;
import com.bughive.dto.ProjectResponse;
import com.bughive.entity.Project;
import com.bughive.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public ProjectResponse toDto(Project p) {
        ProjectResponse dto = new ProjectResponse();

        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setCreatedAt(p.getCreatedAt());
        dto.setIssueCount(0L); // Default to 0 for single fetch, or use separate query if needed

        if (p.getCreatedBy() != null) {
            dto.setCreatedById(p.getCreatedBy().getId());
            dto.setCreatedByName(p.getCreatedBy().getName());
        }
        return dto;
    }

    public ProjectResponse toDto(Project p, Long issueCount) {
        ProjectResponse dto = toDto(p);
        dto.setIssueCount(issueCount);
        return dto;
    }

    public Project toEntity(ProjectRequest request, User createdBy) {
        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setCreatedBy(createdBy);
        return project;
    }

}