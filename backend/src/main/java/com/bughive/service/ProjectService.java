package com.bughive.service;

import com.bughive.dto.ProjectResponse;
import com.bughive.entity.Project;
import com.bughive.mapper.ProjectMapper;
import com.bughive.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

//    createProject
    public ProjectResponse createProject(Project project){
        if(project.getCreatedAt() == null){
            project.setCreatedAt(LocalDateTime.now());
        }
        return projectMapper.toDto(projectRepository.save(project));
    }
//    getAllProjects
    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAllWithCreator()
                .stream()
                .map(projectMapper::toDto)
                .toList();
    }

    public ProjectResponse getProject(Long id) {
        return projectMapper.toDto(projectRepository.findByIdWithCreator(id));
    }
}