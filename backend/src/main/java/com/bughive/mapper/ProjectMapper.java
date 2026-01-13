package com.bughive.mapper;

import com.bughive.dto.ProjectResponse;
import com.bughive.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public ProjectResponse toDto(Project p) {
        ProjectResponse dto = new ProjectResponse();

        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setCreatedAt(p.getCreatedAt());

        if (p.getCreatedBy() != null) {
            dto.setCreatedById(p.getCreatedBy().getId());
            dto.setCreatedByName(p.getCreatedBy().getName());
        }
        return dto;
    }
}
