package com.taskhub.dto;


import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskInfo {
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime creationDate;
    private LocalDateTime deadline;
}
