package com.bughive.service;

import com.bughive.entity.Project;
import com.bughive.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
//    createProject(Project project)
//    getAllProjects()
//    getProjectById(Long id)

    private final ProjectRepository projectRepository;

    public Project createProject(Project project){
        if (project.getCreatedAt() == null) {
            project.setCreatedAt(java.time.LocalDateTime.now());
        }
        return projectRepository.save(project);
    }
    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id){
        return projectRepository.findById(id);
    }
}
