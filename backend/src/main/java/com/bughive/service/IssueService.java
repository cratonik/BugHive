package com.bughive.service;

import com.bughive.dto.IssueRequest;
import com.bughive.dto.IssueResponse;
import com.bughive.entity.Issue;
import com.bughive.entity.Project;
import com.bughive.entity.User;
import com.bughive.mapper.IssuseMapper;
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
    private final IssuseMapper issuseMapper;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public IssueResponse createIssue(IssueRequest issue){
//        return issueRepository.save(issue);
        // 1. Validate mandatory projectId
        if (issue.getProjectId() == null) {
            throw new IllegalArgumentException("projectId is required");
        }
        // 2. Load Project entity
        Project project = projectRepository.findById(issue.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        // 3. Create Issue entity
        Issue new_issue = new Issue();
        new_issue.setTitle(issue.getTitle());
        new_issue.setDescription(issue.getDescription());
        new_issue.setStatus(issue.getStatus());
        new_issue.setPriority(issue.getPriority());
        new_issue.setProject(project);

        // 4. Optional assignee
        if (issue.getAssigneeId() != null) {
            User assignee = userRepository.findById(issue.getAssigneeId())
                    .orElseThrow(() -> new IllegalArgumentException("Assignee not found"));
            new_issue.setAssignee(assignee);
        }
        // 5. Save and map
        return issuseMapper.toDto(issueRepository.save(new_issue));
    }

//    public Optional<IssueResponse> getIssueById(Long id) {
////         return issueRepository.findById(id);
//        return Optional.ofNullable(issuseMapper.toDto(issueRepository.findById(id)));
//    }
        @Transactional(readOnly = true)
      public Optional<IssueResponse> getIssueById(Long id) {
            return issueRepository.findIssueById(id)
                    .map(issuseMapper::toDto);
      }


    public @Nullable List<IssueResponse> getIssuesByProjectId(Long projectId) {
        List<Issue> allIssues = issueRepository.findAll();
        System.out.println(allIssues.toString());   // Debug
        if (allIssues == null)
            return null;
        List<IssueResponse> issueList = allIssues.stream()
                .filter( issue -> issue.getProject().getId().equals(projectId))
                .map(issuseMapper::toDto)
                .toList();
//
        return  issueList;

    }
}
