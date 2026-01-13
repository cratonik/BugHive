package com.bughive.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProjectResponse {

    private Long id;
    private String name;
    private String description;

    private Long createdById;
    private String createdByName;

    private LocalDateTime createdAt;
}
