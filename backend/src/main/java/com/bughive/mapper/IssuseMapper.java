package com.bughive.mapper;

import com.bughive.dto.IssueResponse;
import com.bughive.entity.Issue;
import org.springframework.stereotype.Component;

//@Component
//public class IssuseMapper {
//    public IssueResponse toDto(Issue i) {
//        IssueResponse idto = new IssueResponse();
//
//        idto.setId(i.getId());
//        idto.setTitle(i.getTitle());
//        idto.setPriority(i.getPriority());
//        idto.setDescription(i.getDescription());
//        idto.setStatus(i.getStatus());
//        idto.setProjectName(i.getProject().getName());
//        idto.setProjectId(i.getProject().getId());
//        idto.setAssigneeId(i.getAssignee().getId());
//
//        return idto;
//    }
//}
@Component
public class IssuseMapper {

    public IssueResponse toDto(Issue issue) {
        IssueResponse dto = new IssueResponse();

        dto.setId(issue.getId());
        dto.setTitle(issue.getTitle());
        dto.setDescription(issue.getDescription());
        dto.setStatus(issue.getStatus());
        dto.setPriority(issue.getPriority());
//        dto.setCreatedAt(issue.setCreatedAt());

//        // ✅ Project (safe)
//        if (issue.getProject() != null) {
//            dto.setProjectId(issue.getProject().getId());
//            dto.setProjectName(issue.getProject().getName());
//        }
//
//        // ✅ Assignee (nullable by design)
//        if (issue.getAssignee() != null) {
//            dto.setAssigneeId(issue.getAssignee().getId());
//        } else {
//            dto.setAssigneeId(null);
//        }

        // Project (guaranteed by fetch join)
        dto.setProjectId(issue.getProject().getId());
        dto.setProjectName(issue.getProject().getName());

        // Assignee (nullable by design)
        dto.setAssigneeId(
                issue.getAssignee() != null ? issue.getAssignee().getId() : null
        );

        return dto;
    }
}
