package com.bughive.repository;

import com.bughive.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project p JOIN FETCH p.createdBy")
    List<Project> findAllWithCreator();

    @Query("SELECT p FROM Project p JOIN FETCH p.createdBy WHERE p.id = :id")
    Project findByIdWithCreator(Long id);

    @Query("""
                SELECT p, COUNT(i)
                FROM Project p
                LEFT JOIN Issue i ON i.project.id = p.id
                GROUP BY p
            """)
    List<Object[]> findAllProjectsWithIssueCounts();

}
