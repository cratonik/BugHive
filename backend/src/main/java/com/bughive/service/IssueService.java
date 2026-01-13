package com.bughive.service;

import com.bughive.entity.Issue;
import com.bughive.repository.IssueRepository;
import com.bughive.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IssueService {

//    createIssue(Issue issue)
//    getIssuesByProject(Long projectId)
//    getIssueById(Long id)

    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;


    public Issue createIssue(Issue issue){
        if(issue.getProject() == null){
            throw new IllegalArgumentException("Issue must be associated with a project");
        }
        return issueRepository.save(issue);
    }

    public List<Issue> getIssuesByProject(Long projectId){
        if(!projectRepository.existsById(projectId) ){
            throw new IllegalArgumentException("Project not found by ID: "+ projectId);
        }
        return issueRepository.findByProjectIdOrderByCreatedAtDesc(projectId);

    }

    public Optional<Issue> getIssueById(Long id){
        return issueRepository.findById(id);
    }



}
