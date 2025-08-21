package com.taskhub.dto;


import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskInfo {
    private String title;
    private String description;
    private String completed;
    private String creationDate;
    private String deadline;
}
