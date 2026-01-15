package com.bughive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {

    private Long id;
    private String name;
    private String description;

    private Long createdById;
    private String createdByName;

    private Long issueCount;
    private LocalDateTime createdAt;
}
