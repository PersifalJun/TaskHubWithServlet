package com.taskhub.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskInfo {
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime creationDate;
    private LocalDateTime deadline;
}
