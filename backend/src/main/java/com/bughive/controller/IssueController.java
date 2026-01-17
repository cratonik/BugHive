package com.bughive.controller;

import com.bughive.dto.IssueRequest;
import com.bughive.dto.IssueResponse;
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

    public final IssueService issueService;

    @PostMapping("/issues")
    public ResponseEntity<IssueResponse> creatIssue(@Valid @RequestBody IssueRequest issue){
        IssueResponse savedIssue = issueService.createIssue(issue);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("{/id}")
                        .buildAndExpand(savedIssue.getId())
                        .toUri();

        return  ResponseEntity.created(location).body(savedIssue);
    }

    @GetMapping("/issues/{id}")
    public ResponseEntity<IssueResponse> getIssueById(@PathVariable Long id){
        return issueService.getIssueById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional(readOnly = true)
    @GetMapping("/projects/{projectId}/issues")
    public ResponseEntity<List<IssueResponse>> getIssuesByProjectId(@PathVariable Long projectId){
        @Nullable List<IssueResponse> issues =  issueService.getIssuesByProjectId(projectId);
        return ResponseEntity.ok(issues);

    }
}
