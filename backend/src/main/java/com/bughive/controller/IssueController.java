package com.bughive.controller;

import com.bughive.dto.IssueRequest;
import com.bughive.dto.IssueResponse;
import com.bughive.entity.Issue;
import com.bughive.service.IssueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class IssueController {
    //        createIssue(Issue issue)
    //        getIssuesByProject(Long projectId)
    //        getIssueById(Long id)
    //        Validate issue.project != null.
    public final IssueService issueService;

    @PostMapping("/issues")
    public ResponseEntity<IssueResponse> creatIssue(@Valid @RequestBody IssueRequest issue){
        // Adding the DTO for each and every entity. Entity exposes your database schema directly to the public.
        // A hacker could send a JSON including restricted fields like id
        IssueResponse savedIssue = issueService.createIssue(issue);

        // 2. Dynamic Location Header generation and expand means replace the {id} -> value(12)
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("{/id}")
                        .buildAndExpand(savedIssue.getId())
                        .toUri();
        // 3. Return 201 Created with Location header and Body
        return  ResponseEntity.created(location).body(savedIssue);
    }

    @GetMapping("/issues/{id}")
    public ResponseEntity<IssueResponse> getIssueById(@PathVariable Long id){
        return issueService.getIssueById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //**** IMP **** Change the Id of project to the {projectId}
    @Transactional(readOnly = true)
    @GetMapping("/projects/{projectId}/issues")
    public ResponseEntity<List<IssueResponse>> getIssuesByProjectId(@PathVariable Long projectId){
        @Nullable List<IssueResponse> issues =  issueService.getIssuesByProjectId(projectId);
        return ResponseEntity.ok(issues);

    }
}
