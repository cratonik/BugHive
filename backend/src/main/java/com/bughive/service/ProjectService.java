package com.bughive.service;

import com.bughive.dto.ProjectRequest;
import com.bughive.dto.ProjectResponse;
import com.bughive.entity.Project;
import com.bughive.entity.User;
import com.bughive.mapper.ProjectMapper;
import com.bughive.repository.ProjectRepository;
import com.bughive.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final UserRepository userRepository;

//    createProject
public ProjectResponse createProject(ProjectRequest request) {
    User user = userRepository.findById(request.getCreatedById())
            .orElseThrow(() -> new RuntimeException("User not found"));

    Project project = projectMapper.toEntity(request, user);
    Project saved = projectRepository.save(project);

    return projectMapper.toDto(saved);
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