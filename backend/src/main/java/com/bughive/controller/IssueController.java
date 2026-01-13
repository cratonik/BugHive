package com.bughive.controller;


import com.bughive.entity.Issue;
import com.bughive.service.IssueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @PostMapping("/api/issues")
    public ResponseEntity<Issue> createIssue(@Valid @RequestBody Issue issue){
        Issue saved = issueService.createIssue(issue);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION,"/api/issues/" + saved.getId());
        return ResponseEntity.created(null).headers(headers).body(saved);
    }

    @GetMapping("/api/projects/{projectId}/issues")
    public List<Issue> getIssuesByProject(@PathVariable Long projectId){
        return issueService.getIssuesByProject(projectId);
    }
    @GetMapping("/api/issues/{id}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long id){
        return issueService.getIssueById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
