package com.bughive.repository;

import com.bughive.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByProjectIdOrderByCreatedAtDesc(Long projectId);

//    @Query("""
//        SELECT i FROM Issue i
//        JOIN FETCH i.project p
//        LEFT JOIN FETCH i.assignee a
//        WHERE i.id = :id
//    """)
//Optional<Issue> findIssueById(@Param("id") Long id);
Optional<Issue> findIssueById( Long id);
}
