package com.bughive.service;

import com.bughive.dto.IssueRequest;
import com.bughive.dto.IssueResponse;
import com.bughive.entity.Issue;
import com.bughive.entity.Project;
import com.bughive.entity.User;
import com.bughive.mapper.IssueMapper;
import com.bughive.repository.IssueRepository;

import com.bughive.repository.ProjectRepository;
import com.bughive.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;
    private final IssueMapper issueMapper;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public IssueResponse createIssue(IssueRequest issue) {

        if (issue.getProjectId() == null) {
            throw new IllegalArgumentException("projectId is required");
        }

        Project project = projectRepository.findById(issue.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
        // 3. Optional assignee
        User assignee = null;
        if (issue.getAssigneeId() != null) {
            assignee = userRepository.findById(issue.getAssigneeId())
                    .orElseThrow(() -> new IllegalArgumentException("Assignee not found"));
        }

        // 4. Map to Entity using Mapper (Best Practice)
        Issue new_issue = issueMapper.toEntity(issue, project, assignee);

        // 5. Save and map back to DTO
        return issueMapper.toDto(issueRepository.save(new_issue));
    }

    @Transactional(readOnly = true)
    public Optional<IssueResponse> getIssueById(Long id) {
        return issueRepository.findIssueById(id)
                .map(issueMapper::toDto);
    }

    public @Nullable List<IssueResponse> getIssuesByProjectId(Long projectId) {
        return issueRepository.findAllByProjectId(projectId)
                .stream()
                .map(issueMapper::toDto)
                .toList();
    }
}
