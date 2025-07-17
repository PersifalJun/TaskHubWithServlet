package com.taskhub.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "completed")
    private boolean completed;


    private LocalDateTime createdAt;

    private LocalDateTime deadline;

    @ManyToOne
    @JoinColumn(name = "task_list_id" )
    private TaskList taskList;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

}
