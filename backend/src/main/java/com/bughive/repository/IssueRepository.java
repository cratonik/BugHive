package com.bughive.repository;

import com.bughive.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByProjectIdOrderByCreatedAtDesc(Long projectId);
}
