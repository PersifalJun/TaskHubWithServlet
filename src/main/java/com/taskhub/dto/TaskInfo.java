package com.taskhub.dto;

import com.taskhub.entity.Task;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TaskInfo {
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime creationDate;
    private LocalDateTime deadline;
}
